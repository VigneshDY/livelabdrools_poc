package com.livelabdrools.configuartion;

import com.livelabdrools.application.Application;
import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.mapper.RuleMapper;
import com.livelabdrools.model.Rule;
import com.livelabdrools.rule.DroolsRuleEngine;
import com.livelabdrools.rule.RuleEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class DroolsConfiguration { private DataMapper getPersonMapper() {
    return new PersonMapper();
}

    private RuleEngine getRuleEngine() {
        RuleEngine ruleEngine = new DroolsRuleEngine();
        return ruleEngine;
    }

    @Bean("appDrools")
    public Application getApplication() {
        Application app = new Application();
        app.setDataMapper(getPersonMapper());
        app.setRuleEngine(getRuleEngine());
        return app;
    }


}
