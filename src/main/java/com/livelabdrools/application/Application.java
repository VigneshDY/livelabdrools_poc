package com.livelabdrools.application;

import com.livelabdrools.configuartion.SpringELConfiguration;
import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.model.Person;
import com.livelabdrools.rule.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.List;

public class Application {

    private DataMapper dataMapper;

    private RuleEngine ruleEngine;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringELConfiguration.class);
        DataMapper dataMapper1=context.getBean(DataMapper.class);
        String fileName=args[0];
        File file=new File("fileName");


    }

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
}
