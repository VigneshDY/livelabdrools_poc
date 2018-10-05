package com.livelabdrools.rule;

import java.util.List;

import com.livelabdrools.model.Person;

public interface RuleEngine {
    public abstract  List<Person> processData(List<Person> personList);
}
