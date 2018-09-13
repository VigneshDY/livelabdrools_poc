package com.cts.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.cts.model.Person;
import com.cts.model.Rule;

public class RuleEngine {
	public RuleEngine() {
	}

	List<Rule> rulesList;
	Map<Rule, String> inputExpMap = new LinkedHashMap<Rule, String>();

	public RuleEngine(List<Rule> rulesList) {
		this.rulesList = rulesList;
		// Rule rule = new Rule();
		for (Rule rule : rulesList) {
			inputExpMap.put(rule, parseInputRule(rule.getInput()));
		}
		// for (Rule rule : rulesList) {
		// outputExpMap.put(rule, rule.getOutput());
		// }
		for (Map.Entry<Rule, String> entry : inputExpMap.entrySet()) {
			System.out.println("Rule------" + entry.getKey() + "Expression---" + entry.getValue());
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
			bufferString.append(value);
			System.out.println(bufferString);
		}

		System.out.println(bufferString);
		return bufferString.toString();
	}

	public void applyRule(List<Person> personList) {
ExpressionParser expParser = new SpelExpressionParser();


	}

}
