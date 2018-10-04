package com.livelabdrools.rule;

import com.livelabdrools.model.Person;

import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import java.util.List;

public class DroolsRuleEngine implements RuleEngine{
	private static final Logger log = Logger.getLogger(DroolsRuleEngine.class);
    public void processData(List<Person> personList) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rule");
        for (Person person : personList) {
            kSession.insert(person);
        }
        kSession.fireAllRules(personList.size());
        log.info(personList);
    }

}
