package com.livelabdrools.rule;

import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.mapper.PersonMapperTest;
import com.livelabdrools.mapper.RuleMapper;
import com.livelabdrools.model.Person;
import com.livelabdrools.model.RuleFact;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class SpringRuleEngineTest {

    String fileName = "SpringEL_Rule.xlsx";
    ClassLoader classLoader = this.getClass().getClassLoader();
    File inputFile = new File(classLoader.getResource(fileName).getFile());
    @Test
    public void testParseInputRule() {
        SpringRuleEngine springRuleEngine = new SpringRuleEngine();
        List<RuleFact> ruleFactList = new ArrayList<>();
        RuleFact ruleFact = new RuleFact();
        RuleMapper ruleMapper = new RuleMapper();
        ruleMapper.init();
        ruleFact.setAttribute("location");
        ruleFact.setOperator("EQ");
        ruleFact.setValue("Chennai");

        RuleFact ruleFact1 = new RuleFact();
        ruleFact1.setAttribute("abc");
        ruleFact1.setOperator("GT");
        ruleFact1.setValue("ghi");

        ruleFactList.add(ruleFact);
        ruleFactList.add(ruleFact1);

        String value="";
       if(ruleFactList.get(0).getOperator().name().equalsIgnoreCase("EQ")) {
           value = ruleFactList.get(0).getAttribute() + " " + "matches" + " " + "'"+ruleFactList.get(0).getValue()+"'";
       }
       else
       {
           value=value = ruleFactList.get(0).getAttribute() + " " + ruleFactList.get(0).getOperator() + " " + "'"+ruleFactList.get(0).getValue()+"'";
       }
       String value1="";
        if(ruleFactList.get(1).getOperator().name().equalsIgnoreCase("EQ")) {
            value1 = ruleFactList.get(1).getAttribute() + " " + "matches" + " " + "'"+ruleFactList.get(1).getValue()+"'";
        }
        else
        {
            value1= ruleFactList.get(1).getAttribute() + " " + ruleFactList.get(1).getOperator() + " " + "'"+ruleFactList.get(1).getValue()+"'";
        }
        assertEquals(value, springRuleEngine.parseInputRule(ruleMapper.getData(inputFile).get(0).getInput()).getExpressionString());
       assertFalse(value1.equals(springRuleEngine.parseInputRule(ruleMapper.getData(inputFile).get(0).getInput()).getExpressionString()));
    }

    @Test
    public void testProcessData()
    {
        String fileName = "Person.psv";
        ClassLoader classLoader = this.getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource(fileName).getFile());

        SpringRuleEngine springRuleEngine=new SpringRuleEngine();
        PersonMapper personMapper= new PersonMapper();

        List<Person> personList=new ArrayList<>();
        Person person=new Person();
        person.setId();
        person.setFirstName();
        person.setLastName();
        person.setLocation();
        person.setTimeZone();
        personList.add(person);

        assertEquals(personList.get(0),personMapper.getData(inputFile).get(0));
        assertFalse(personList.get(1).equals(personMapper.getData(inputFile).get(0)));
    }
}

