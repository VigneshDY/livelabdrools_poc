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
		RuleMapper ruleMapper=new RuleMapper();
		List<Rule> person=new ArrayList<Rule> ();
		File inputFile=new File("C:\\Users\\674448\\Desktop\\SpringEL_Rule.xlsx");
		String[] fileParts = inputFile.getName().split("\\.");
		String ext = fileParts[fileParts.length-1];
		ExcelReader excelReader=new ExcelReader();
		List<String[]> data=excelReader.getData(inputFile, 2);
		List<String[]> header=excelReader.getHeader(inputFile, 2);
		
		List<Rule> ruleList=new ArrayList<Rule>();
		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
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
			index++;
		}
		for(int i=0;i<header1.length;i++)
		{
			System.out.println(header1[i]);
		}
		int cellIndex=0;
		for(String[] data1:data)
		{
			List<RuleFact> ruleFactList=new ArrayList<RuleFact>();
			cellIndex=0;
			for(int i=0;i<data1.length;i++)
			{
				RuleFact rf=new RuleFact();
				rf.setAttribute(header1[cellIndex]);
				rf.setOperator(header1[cellIndex+2]);
				rf.setValue(data1[i]);
				cellIndex++;
				ruleFactList.add(rf);
			}
		
		Rule rule1=new Rule(ruleFactList);
		ruleList.add(rule1);

	}
	
		assertEquals(ruleList.get(0).getInput().get(0).getAttribute(),ruleMapper.getData(inputFile).get(0).getInput().get(0).getAttribute());
		assertEquals(ruleList.get(0).getInput().get(0).getOperator(),ruleMapper.getData(inputFile).get(0).getInput().get(0).getOperator());
		assertEquals(ruleList.get(0).getInput().get(0).getValue(),ruleMapper.getData(inputFile).get(0).getInput().get(0).getValue());
		
		
	}

}
