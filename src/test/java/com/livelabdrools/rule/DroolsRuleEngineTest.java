package com.livelabdrools.rule;

import com.livelabdrools.model.Person;
import com.livelabdrools.model.Rule;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;


public class DroolsRuleEngineTest {
    @Test
    public void processDataTest(List<Person> personList){
       /* RuleEngine ruleEngine = new DroolsRuleEngine();
        for(Person person : personList)
        {
            person.setTimeZone("IST");
        }
        assertEquals(personList, ruleEngine.processData(personList));*/
    }
}
