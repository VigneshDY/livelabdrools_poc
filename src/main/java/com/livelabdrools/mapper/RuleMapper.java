package com.livelabdrools.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import com.livelabdrools.reader.ExcelReader;

public class RuleMapper extends DataMapper {

	private ExcelReader excelReader;

	@Override
	public List<Object> getData(List<String[]> header, List<String[]> data) {
	
		List<Object> rule=new ArrayList<Object>();
		List<RuleFact> ruleFact=new ArrayList<RuleFact> ();
		
		Map<String,Integer> map=new HashMap<String,Integer> ();
		int j=0;
		for(String[] head:header)
		{
			for(int i=0;i<head.length;i++)
			{
				map.put(head[i], j);
				j++;
			}
			
		}
		String header1[]=new String[j];
		int index=0;
		
		for(Map.Entry<String, Integer> m:map.entrySet())
		{
			header1[index]=m.getKey();
		}
		int cellIndex=0;
		for(String[] data1:data)
		{
			cellIndex=0;
			for(int i=0;i<data1.length;i++)
			{
				RuleFact rf=new RuleFact();
				rf.setAttribute(header1[cellIndex]);
				rf.setOperator(header1[cellIndex+2]);
				rf.setValue(data1[i]);
				cellIndex++;
				ruleFact.add(rf);
			}
		
		Rule rule1=new Rule(ruleFact);
		rule.add(rule1);
		
		
	
	}
		return rule;
	}
}


