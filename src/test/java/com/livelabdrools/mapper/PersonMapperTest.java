package com.livelabdrools.mapper;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.livelabdrools.model.Data;
import com.livelabdrools.model.Person;
import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.reader.ReadFile;

public class PersonMapperTest {

	@Test
	public void testGetData() {
		
		PersonMapper personMapper=new PersonMapper();
		List<Person> person=new ArrayList<Person> ();
		File inputFile=new File("C:\\Users\\674448\\Desktop\\personinput.psv");
		String[] fileParts = inputFile.getName().split("\\.");
		String ext = fileParts[fileParts.length-1];
		DelimiterReader delimiterReader=new DelimiterReader("\\|");
		List<String[]> data=delimiterReader.getData(inputFile, 1);		
		
		for(String[] data1:data)
		{
			for(int i=0;i<data1.length;i++)
			{
				Person person1=new Person();
				person1.setId(data1[i]);
				person1.setFirstName(data1[i+1]);
				person1.setLastName(data1[i+2]);
				person1.setLocation(data1[i+3]);
				person1.setTimeZone(null);
				person.add(person1);
				break;
			}
		}
		assertEquals(person.get(0).getFirstName(), personMapper.getData(inputFile).get(0).getFirstName());
		assertEquals(person.get(0).getLastName(), personMapper.getData(inputFile).get(0).getLastName());
		assertEquals(person.get(0).getId(), personMapper.getData(inputFile).get(0).getId());
		assertEquals(person.get(0).getLocation(), personMapper.getData(inputFile).get(0).getLocation());
		assertEquals(person.get(0).getTimeZone(), personMapper.getData(inputFile).get(0).getTimeZone());
		
	}



}
