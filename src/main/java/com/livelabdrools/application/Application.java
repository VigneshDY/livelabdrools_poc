package com.livelabdrools.application;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.model.Person;
import com.livelabdrools.rule.RuleEngine;
import com.livelabdrools.utility.TimeTracker;


@RestController
@EnableAutoConfiguration
@RequestMapping("/app/rule")
public class Application {
	
	private static final Logger log = Logger.getLogger(Application.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	@Qualifier("personMapper")
	private DataMapper dataMapper;

	@Autowired
	@Qualifier("springRuleEngine")
	private RuleEngine springRuleEngine;
	
	@Autowired
	@Qualifier("droolsRuleEngine")
	private RuleEngine droolsRuleEngine;

	@RequestMapping("/springel")
	public @ResponseBody String processDataWithSpringEl(@RequestParam(name="filePath") String inputFile) {
		TimeTracker timeTracker = new TimeTracker();
		String response = processData(inputFile, springRuleEngine);
		log.info("Application total time " + timeTracker.getProcessTime());
		return response;
	}
	
	@RequestMapping("/drools")
	public @ResponseBody String processDataWithDrools(@RequestParam(name="filePath") String inputFile) {
		TimeTracker timeTracker = new TimeTracker();
		String response = processData(inputFile, droolsRuleEngine);
		log.info("Application total time " + timeTracker.getProcessTime());
		return response;
	}
	
	private String processData(String inputFile, RuleEngine ruleEngine) {
		File file = new File(inputFile);
		List<Person> objToProcess = dataMapper.getData(file);
		List<Person> personList = droolsRuleEngine.processData(objToProcess);
		return gson.toJson(personList);
	}
	
}
