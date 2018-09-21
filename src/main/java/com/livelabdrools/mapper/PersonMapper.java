package com.livelabdrools.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Person;
import com.livelabdrools.reader.DelimiterReader;

public class PersonMapper extends DataMapper{

	List<Person> listPerson=new ArrayList<Person>();
	private DelimiterReader delimiterReader;
	public List getData(String line) {
		// TODO Auto-generated method stub
		String temp[]=new String[5];
		Map<String,String> map=new HashMap<String,String> ();
		String header=delimiterReader.getHeader();
		String arr[]=header.split("\\|");
		Integer length = 0;
		String count = "0";
		for (int j = 0; j < arr.length; j++) {
			map.put(arr[j], count);
			length++;
			count = length.toString();
		}
		String arr1[] = line.split("\\|");
		Person person1 = new Person();
		// int k = 0;
		person1.setId(arr1[Integer.parseInt(map.get(temp[0]))]);
		person1.setFirstName(arr1[Integer.parseInt(map.get(temp[1]))]);
		person1.setLastName(arr1[Integer.parseInt(map.get(temp[2]))]);
		person1.setLocation(arr1[Integer.parseInt(map.get(temp[3]))]);
		person1.setTimeZone(null);
		listPerson.add(person1);
		return listPerson;
	}

}
