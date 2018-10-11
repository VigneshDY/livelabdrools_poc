package com.livelabdrools.mapper;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.livelabdrools.model.Person;
import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.reader.ExcelReader;

public class RuleMapperTest {

	@Test
	public void testGetData() {
		RuleMapper ruleMapper = new RuleMapper();
		List<Rule> ruleList = new ArrayList<Rule>();

		String fileName = "SpringEL_Rule.xlsx";
		ClassLoader classLoader = new RuleMapperTest().getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource(fileName).getFile());

		List<RuleFact> ruleFactList = new ArrayList<RuleFact>();
		RuleFact ruleFact = new RuleFact();
		ruleFact.setAttribute("location");
		ruleFact.setOperator("EQ");
		ruleFact.setValue("Chennai");
		ruleFactList.add(ruleFact);
		Rule rule = new Rule(ruleFactList);
		ruleList.add(rule);
		assertEquals(ruleMapper.getData(inputFile).get(0).getInput().get(0).getAttribute(),ruleList.get(0).getInput().get(0).getAttribute());
		assertEquals(ruleMapper.getData(inputFile).get(0).getInput().get(0).getOperator(),ruleList.get(0).getInput().get(0).getOperator());
		assertEquals(ruleMapper.getData(inputFile).get(0).getInput().get(0).getValue(),ruleList.get(0).getInput().get(0).getValue());
		RuleFact ruleFact1=new RuleFact();
		ruleFact1.setAttribute("ocation");
		ruleFact1.setOperator("GT");
		ruleFact1.setValue("New Delhi");
		ruleFactList.add(ruleFact1);
		Rule rule1=new Rule(ruleFactList);
		ruleList.add(rule1);
		assertFalse(ruleMapper.getData(inputFile).get(0).getInput().get(0).getAttribute()==ruleList.get(1).getInput().get(1).getAttribute());
		assertFalse(ruleMapper.getData(inputFile).get(0).getInput().get(0).getOperator()==ruleList.get(1).getInput().get(1).getOperator());
		assertFalse(ruleMapper.getData(inputFile).get(0).getInput().get(0).getValue()==ruleList.get(1).getInput().get(1).getValue());
	}

}
