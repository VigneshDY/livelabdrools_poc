package com.livelabdrools.rule;

import com.livelabdrools.model.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import java.util.List;

public class DroolsRuleEngine implements RuleEngine{

    public List<Person> processData(List<Person> personList) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rule");
        for (Person person : personList) {
            kSession.insert(person);
        }
        kSession.fireAllRules(personList.size());
        return personList;
    }

}
