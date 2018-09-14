package com.cts.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.cts.model.Person;
import com.cts.model.Rule;

public class RuleEngine {

	List<Rule> rulesList;
	Map<Rule, String> inputExpMap = new LinkedHashMap<Rule, String>();

	public RuleEngine() {

	}

	public RuleEngine(List<Rule> rulesList) {
		this.rulesList = rulesList;
		for (Rule rule : rulesList) {
			inputExpMap.put(rule, parseInputRule(rule.getInput()));
		}
		for (Map.Entry<Rule, String> entry : inputExpMap.entrySet()) {
			System.out.println("---Rule---" + entry.getKey() + "---Expression---" + entry.getValue());
		}

	}

	private String parseInputRule(List<RuleFact> input) {
		StringBuffer bufferString = new StringBuffer();

		for (RuleFact ruleFact : input) {
			String value = ruleFact.getValue();
			bufferString = bufferString.append(ruleFact.getAttribute()).append(" ");
			if ("EQ".equalsIgnoreCase(ruleFact.getOperator().name())) {
				bufferString.append("matches");
			}
			bufferString.append(" ");
			if (ruleFact.getValue().contains("%")) {
				value = value.replace("%", ".*");
			}
			bufferString.append("'"+value+"'");
		}

		return bufferString.toString();
	}

	public void applyRule(List<Person> personList) {
		for (Person person : personList) {
			for (Rule rule : rulesList) {
				String exp = inputExpMap.get(rule);
				//String exp2 = exp.replace("location", "'location'");
				ExpressionParser expParser = new SpelExpressionParser();
				//Expression expression = expParser.parseExpression(exp2).getValue(p,boolean.class);
				boolean value = expParser.parseExpression(exp).getValue(person,boolean.class);
				
				//System.out.println("hi"+rule);
				//System.out.println(value);
				if(value)
				{
					person.setTimeZone(rule.getOutput().get(0).getValue().toString());
				//	System.out.println(rule.getOutput().get(0).getValue().toString());
					break;
				}
			}
		
			System.out.println(person);
			}
		}
		/*
		 * ExpressionParser expParser = new SpelExpressionParser(); for (Person
		 * p : personList) { Expression expression =
		 * expParser.parseExpression(inputExpMap.); } }
		 */

	
}
