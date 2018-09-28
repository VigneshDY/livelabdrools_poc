package com.livelabdrools.mapper;

import java.io.File;
import java.util.*;

import com.livelabdrools.model.Data;
import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;
import com.livelabdrools.reader.ExcelReader;
import com.livelabdrools.reader.ReadFile;

public class RuleMapper extends DataMapper {

	private ExcelReader excelReader;

	@Override
	public List<Rule> getData(File inputFile) {
		System.out.println(inputFile.getName());
		String[] fileParts = inputFile.getName().split("\\.");
		String ext = fileParts[fileParts.length-1];
		ReadFile fileReader = fileReaders.get(ext);
		Data data = fileReader.readFile(inputFile,2);
		List<String[]> dataList=data.getData();
		List<String[]> headerList=data.getHeader();
		List<Rule> ruleList=new ArrayList<Rule>();
		List<String> header=new ArrayList<String>();
		for(String[] head:headerList)
		{
			for(int i=0;i<head.length;i++)
			{
				header.add(head[i]);
			
		}
		}
			int cellIndex=0;
		for(String[] data1:dataList)
		{
			List<RuleFact> ruleFactList=new ArrayList<RuleFact>();
			cellIndex=0;
			for(int i=0;i<data1.length;i++)
			{
				RuleFact rf=new RuleFact();
				rf.setAttribute(header.get(cellIndex));
				rf.setOperator(header.get(cellIndex+2));
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


