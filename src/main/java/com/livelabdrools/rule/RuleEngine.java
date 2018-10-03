package com.livelabdrools.rule;

import com.livelabdrools.model.Person;
import com.livelabdrools.model.RuleFact;

import java.util.List;

public interface RuleEngine {
    public abstract  List<Person> processData(List<Person> personList);

}
