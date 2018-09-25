package com.livelab_drools.application;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.livelab_drools.model.Person;
import com.livelab_drools.service.Service;

import utility.TimeTracker;

@SpringBootApplication
@ComponentScan("com.livelab_drools")
public class Application {

	public static void main(String[] args) {
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
			//ApplicationContext context
			ConfigurableApplicationContext context=SpringApplication.run(Application.class,args);
			Service servObj=context.getBean(Service.class);
			//Service servObj = new Service();
			List<Person> personList = servObj.readPsv();
			
			FactHandle fact1;
			for (Person person : personList) {
				fact1 = kSession.insert(person);
			}
			kSession.fireAllRules();
			for (Person personObj : personList) {
				System.out.println(
						"---location--- " + personObj.getLocation() + "---timezone---" + personObj.getTimeZone());
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}

}
