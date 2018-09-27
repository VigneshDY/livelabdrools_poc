package com.livelabdrools.rule;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Person;
import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringELRuleEngine implements RuleEngine{

    private List<Rule> rulesList;
    private Map<Rule, Expression> inputExpMap = new LinkedHashMap<Rule, Expression>();

    public SpringELRuleEngine(List<Rule> rulesList) {
        this.rulesList = rulesList;
        ExpressionParser parser = new SpelExpressionParser();
        for (Rule rule : rulesList) {
            inputExpMap.put(rule, parseInputRule(parser, rule.getInput()));
        }
       }

  public Expression parseInputRule(ExpressionParser parser, List<RuleFact> input) {

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

        return parser.parseExpression(bufferString.toString());

    }

    public List<Person> processData(List<Person> personList) {
        for (Person person : personList) {
            for (Rule rule : rulesList) {

                boolean status = inputExpMap.get(rule).getValue(person, Boolean.class);
                if (status) {
                    person.setTimeZone(rule.getOutput().get(0).getValue().toString());

                    break;
                }
            }


        }
        return personList;
    }
}

