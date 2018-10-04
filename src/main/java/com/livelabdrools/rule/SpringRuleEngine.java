package com.livelabdrools.rule;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Person;
import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;

import org.apache.log4j.Logger;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringRuleEngine implements RuleEngine{
	private static final Logger log = Logger.getLogger(SpringRuleEngine.class);
    private List<Person> personList;
    private List<Rule> rulesList;
    private Map<Rule, String> inputExpMap = new LinkedHashMap<Rule, String>();

    public SpringRuleEngine(List<Rule> rulesList) {
        this.rulesList = rulesList;

        for (Rule rule : rulesList) {
            inputExpMap.put(rule, parseInputRule(rule.getInput()));
        }
       }

  public String parseInputRule(List<RuleFact> input) {
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

        return bufferString.toString();

    }

    public void processData(List<Person> personList) {
        for (Person person : personList) {
            for (Rule rule : rulesList) {
                ExpressionParser parser = new SpelExpressionParser();
                Expression exp = parser.parseExpression(inputExpMap.get(rule));
                boolean status = exp.getValue(person, Boolean.class);
                if (status) {
                    person.setTimeZone(rule.getOutput().get(0).getValue().toString());

                    break;
                }
            }


        }
        for(Person person : personList)
        {
       log.info(person+"\n");
        }
        }
}

