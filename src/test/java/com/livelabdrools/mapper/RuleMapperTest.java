package com.livelabdrools.mapper;


import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RuleMapperTest {

	@Test
	public void testGetData() {
		RuleMapper ruleMapper = new RuleMapper();
		List<Rule> ruleList = new ArrayList<Rule>();
		File inputFile = new File("C:\\Users\\690257\\Desktop\\SpringEL_Rule.xlsx");
		List<RuleFact> ruleFactList = new ArrayList<RuleFact>();
		RuleFact ruleFact = new RuleFact();
		ruleFact.setAttribute("location");
		ruleFact.setOperator("EQ");
		ruleFact.setValue("Chennai");
		ruleFactList.add(ruleFact);
		Rule rule = new Rule(ruleFactList);
		ruleList.add(rule);
		assertEquals(ruleList.get(0).getInput().get(0).getAttribute(),
				ruleMapper.getData(inputFile).get(0).getInput().get(0).getAttribute());
		assertEquals(ruleList.get(0).getInput().get(0).getOperator(),
				ruleMapper.getData(inputFile).get(0).getInput().get(0).getOperator());
		assertEquals(ruleList.get(0).getInput().get(0).getValue(),
				ruleMapper.getData(inputFile).get(0).getInput().get(0).getValue());

	}

}

