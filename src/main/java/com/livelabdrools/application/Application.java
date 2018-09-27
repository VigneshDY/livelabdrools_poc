package com.livelabdrools.application;

import com.livelabdrools.configuartion.DroolsConfiguration;
import com.livelabdrools.configuartion.SpringELConfiguration;
import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.model.Person;
import com.livelabdrools.rule.RuleEngine;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.List;

@EnableAutoConfiguration
public class Application {

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

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringELConfiguration.class);
        Application app = (Application)context.getBean("app");
//        ApplicationContext context = new AnnotationConfigApplicationContext(DroolsConfiguration.class);
  //      Application app = (Application)context.getBean("appDrools");
        app.processData("C:\\Users\\690257\\Desktop\\personinput.psv"/*args[0]*/);
    }

    public void processData(String inputFile) {
        File file=new File(inputFile);
        List<Person> objToProcess = dataMapper.getData(file);
        List<Person> processedObj =        ruleEngine.processData(objToProcess);
        System.out.println(processedObj);
    }


}
