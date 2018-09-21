package com.livelabdrools.mapper;

import java.util.ArrayList;
import java.util.List;

import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import com.livelabdrools.reader.ExcelReader;

public class RuleMapper extends DataMapper {

	private ExcelReader excelReader;

	public List getData(String line) {
		List<RuleFact> ruleFactList = new ArrayList<RuleFact>();
		List<Rule> listRule = new ArrayList<Rule>();
		// TODO Auto-generated method stub
		String attri = excelReader.getHeader();
		String attribute[] = attri.split(" ");
		String operator[] = attri.split("-");

		String input = excelReader.getData();
		String data[] = input.split(" ");

		for (int i = 0; i < data.length; i++) {
			RuleFact ruleFact = new RuleFact();
			if (i % 2 == 0) {
				ruleFact.setAttribute(attribute[0]);
				ruleFact.setOperator(operator[0]);
				ruleFact.setValue(data[i]);
				ruleFactList.add(ruleFact);
			} else {
				ruleFact.setAttribute(attribute[1]);
				ruleFact.setOperator(operator[1]);
				ruleFact.setValue(data[i]);
				ruleFactList.add(ruleFact);
			}
			Rule rule = new Rule(ruleFactList);
			listRule.add(rule);
		}
		/*
		 * ruleFact.setAttribute(attribute[0]);
		 * ruleFact.setOperator(operator[1]); ruleFact.setValue(data[0]);
		 */

		return listRule;
	}

}
