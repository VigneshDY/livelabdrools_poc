package com.livelabdrools.rule;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.mapper.RuleMapper;
import com.livelabdrools.model.Person;
import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;

@Component
@Qualifier("springRuleEngine")
public class SpringRuleEngine implements RuleEngine {
	
	private List<Rule> rulesList;
	
	private Map<Rule, Expression> inputExpMap = new LinkedHashMap<Rule, Expression>();
	
	private ExpressionParser springELParser = new SpelExpressionParser();
	
	@Autowired
	@Qualifier("ruleMapper")
	private DataMapper dataMapper = new RuleMapper();
	
	@PostConstruct
	public void init() {
        
        ClassLoader classLoader = SpringRuleEngine.class.getClassLoader();
        List<Rule> ruleList = (List<Rule>)dataMapper.getData(new File(classLoader.getResource("SpringEL_Rule.xlsx").getFile()));

        for (Rule rule : ruleList) {
			inputExpMap.put(rule, parseInputRule(rule.getInput()));
		}
        
        this.rulesList = ruleList;
	}
	
	public Expression parseInputRule(List<RuleFact> input) {
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
			
			bufferString.append("'" + value + "'");
		}
		return springELParser.parseExpression(bufferString.toString());

	}

	public List<Person> processData(List<Person> personList) {
		for (Person person : personList) {
			for (Rule rule : rulesList) {
				boolean status = inputExpMap.get(rule).getValue(person, Boolean.class);
				if (status) {
					person.setTimeZone(rule.getOutput().get(0).getValue().toString());
					System.out.println(rule.getOutput().get(0).getValue().toString());
					break;
				}
			}
		}
		return personList;
	}
}
