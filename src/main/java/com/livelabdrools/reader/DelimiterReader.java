package com.livelabdrools.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.model.Person;



public class DelimiterReader implements ReadFile{
	private PersonMapper personMapper;
	private static String psv="C:\\Users\\674448\\Desktop\\personinput.psv";
	public String[] getHeader() {
		String input="";
		Integer index=0;
		String arr1[]=new String[100];

		try
		{
		BufferedReader br = new BufferedReader(new FileReader(psv));
		String line = "";
		Person person = new Person();
		int length = 0;
		int j=0;
		while ((line = br.readLine()) != null) {
			
			if(index==0)
			{
				index++;
			
			
			String arr[] = line.split("\\|");
			for(int i=0;i<arr.length;i++)
			{
			arr1[j]=arr[i];
			j++;
			}
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arr1;
	}

	public String[] getData() {
		String input="";
		String arr1[]=new String[100];

		try
		{
			Integer index=0;
			
			BufferedReader br = new BufferedReader(new FileReader(psv));
			String line = "";
			Person person = new Person();
			int length = 0;
			int j=0;
			while ((line = br.readLine()) != null) {
				
					if(index==0)
					{
						index++;
					}
					else
					{
					String arr[] = line.split("\\|");
					for(int i=0;i<arr.length;i++)
					{
					arr1[j]=arr[i];
					j++;
					}
					}

				
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arr1;

}
}
