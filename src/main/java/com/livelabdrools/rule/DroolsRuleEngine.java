package com.livelabdrools.rule;

import java.util.List;

import javax.annotation.PostConstruct;


import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.livelabdrools.model.Person;

@Component
@Qualifier("droolsRuleEngine")
public class DroolsRuleEngine implements RuleEngine {
	
	private KieSession kSession;
	
	@PostConstruct
	public void init() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("ksession-rule");
	}
	
    public List<Person> processData(List<Person> personList) {
        for (Person person : personList) {
        	kSession.insert(person);
        }
        kSession.fireAllRules(personList.size());
        return personList;
    }

}
