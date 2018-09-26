package com.livelabdrools.rule;

import com.livelabdrools.model.Person;
import com.livelabdrools.model.RuleFact;

import java.util.List;

public interface SpringELRuleEngine {

    public void processData (List<Person> personList);
    public String parseInputRule(List<RuleFact> ruleFactList);



}
