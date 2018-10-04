package com.livelabdrools.application;

import com.livelabdrools.configuartion.SpringELConfiguration;
import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.model.Person;
import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.rule.RuleEngine;
import com.livelabdrools.utility.TimeTracker;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
public class Application {
	private static final Logger log = Logger.getLogger(Application.class);
	private DataMapper dataMapper;

	private RuleEngine ruleEngine;

	public DataMapper getDataMapper() {
		return dataMapper;
	}

	public void setDataMapper(DataMapper dataMapper) {
		this.dataMapper = dataMapper;
	}

	public RuleEngine getRuleEngine() {
		return ruleEngine;
	}

	public void setRuleEngine(RuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}

	public static void main(String[] args) throws ParseException {
		
		TimeTracker timeTracker = new TimeTracker();
		SimpleDateFormat sdf = new SimpleDateFormat("SS");
		String startTime = timeTracker.getStartTime().toString();
		Date date = sdf.parse(startTime);
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss.SS");
		log.info("Application start time " + sdf1.format(date));
		log.info("Spring EL Application");
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(SpringELConfiguration.class);
		Application app = (Application) contextSpring.getBean("app");
		//log.info("Drools Application");
		//ApplicationContext contextDrools = new AnnotationConfigApplicationContext(DroolsConfiguration.class);
		//Application app = (Application) contextDrools.getBean("appDrools");
		app.processData("C:\\Users\\674448\\Desktop\\Person.psv"/* args[0] */);
		timeTracker.setEndTime();
		String endTime = timeTracker.getEndTime().toString();
		Date date1 = sdf.parse(endTime);
		log.info("Application end time " + sdf1.format(date1));
		String totalTime = timeTracker.getTotalTimeElapsed().toString();
		Date date2 = sdf.parse(totalTime);
		log.info("Application total time " + sdf1.format(date2));
	}

	public void processData(String inputFile) throws ParseException {
		File file = new File(inputFile);
		List<Person> objToProcess = dataMapper.getData(file);
		ruleEngine.processData(objToProcess);
	}

}
