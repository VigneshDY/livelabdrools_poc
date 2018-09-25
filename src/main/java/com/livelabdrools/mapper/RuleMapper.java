package com.livelabdrools.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Data;
import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import com.livelabdrools.reader.ExcelReader;
import com.livelabdrools.reader.ReadFile;

public class RuleMapper extends DataMapper {

	private ExcelReader excelReader;

	@Override
	public List<Rule> getData(File inputFile) {

		String[] fileParts = inputFile.getName().split(".");
		String ext = fileParts[fileParts.length-1];
		ReadFile fileReader = fileReaders.get(ext);
		Data data = fileReader.readFile(inputFile,2);

		List<String[]> dataList=data.getData();
		List<String[]> headerList=data.getHeader();
		List<RuleFact> ruleFactList=new ArrayList<RuleFact>();
		List<Rule> ruleList=new ArrayList<Rule>();
		Map<String,Integer> map=new HashMap<String,Integer> ();
		int j=0;
		for(String[] head:headerList)
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
		for(String[] data1:dataList)
		{
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
		return ruleList;
	}
}


