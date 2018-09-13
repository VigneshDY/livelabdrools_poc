package com.cts.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cts.model.Rule;

public class RuleEngine {
	Rule rule = new Rule();
	
	/*List<RuleFact> inputList = rule.parseInputRuleFact();
	List<RuleFact> outputList = rule.parseOutputRuleFact();*/
	Map<RuleFact, String> inputExpMap = new LinkedHashMap<RuleFact, String>();
	Map<RuleFact, String> outputExpMap = new LinkedHashMap<RuleFact, String>();

	
	public void parseRulesList(List<RuleFact> inputList,List<RuleFact> outputList) {
		StringBuffer bufferString = new StringBuffer();
		for (RuleFact ruleFact : inputList) {

			bufferString = bufferString.append(ruleFact.getAttribute()).append(ruleFact.getOperator())
					.append(ruleFact.getValue());
			String temp = bufferString.toString();
			inputExpMap.put(ruleFact, temp);

		}
		StringBuffer bufferString1 = new StringBuffer();
		for (RuleFact ruleFact : outputList) {
			bufferString1 = bufferString1.append(ruleFact.getValue());
			String temp = bufferString1.toString();
			outputExpMap.put(ruleFact, temp);
}
		for(Map.Entry<RuleFact,String> m2:inputExpMap.entrySet())
		{
			System.out.println(m2.getKey()+" "+m2.getValue());
		}

	}
	
}
