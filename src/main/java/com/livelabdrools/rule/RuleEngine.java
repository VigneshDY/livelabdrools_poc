package com.livelabdrools.rule;

import com.livelabdrools.model.Person;

import java.util.List;

public interface RuleEngine {
    public abstract  List<Person> processData(List<Person> personList);
}
