package com.livelabdrools.configuartion;

import com.livelabdrools.application.Application;
import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.mapper.RuleMapper;
import com.livelabdrools.model.Rule;
import com.livelabdrools.reader.ReadFile;
import com.livelabdrools.rule.RuleEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class SpringELConfiguration {

    private DataMapper getPersonMapper() {
        return new PersonMapper();
    }

    private RuleEngine getRuleEngine() {
        DataMapper dataMapper = new RuleMapper();
        List<Rule> ruleList = dataMapper.getData(new File("classpath:SpringEL_Rule.xlsx"));
        RuleEngine ruleEngine = new RuleEngine(ruleList);
        return ruleEngine;
    }

    @Bean
    public Application getApplication() {
        Application app = new Application();
        app.setDataMapper(getPersonMapper());
        app.setRuleEngine(getRuleEngine());
        return app;
    }


}
