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
		String fileName = "personinput.psv";
		ClassLoader classLoader = new PersonMapperTest().getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		PersonMapper personMapper = new PersonMapper();
		List<Person> person = new ArrayList<Person>();
		String arr[] ={"1","Sarath","ranganathan","Chennai",null};
		Person per = new Person();
		per.setId(arr[0]);
		per.setFirstName(arr[1]);
		per.setLastName(arr[2]);
		per.setLocation(arr[3]);
		per.setTimeZone(arr[4]);
		person.add(per);
		Person per1 = new Person();
		per1.setFirstName("abc");
		per1.setId("1012");
		per1.setLastName("def");
		per1.setLocation("New Delhi");
		per1.setTimeZone("EST");
		person.add(per1);
		assertEquals(personMapper.getData(file).get(0).getFirstName(), person.get(0).getFirstName());
		assertEquals(personMapper.getData(file).get(0).getLastName(), person.get(0).getLastName());
		assertEquals(personMapper.getData(file).get(0).getId(), person.get(0).getId());
		assertEquals(personMapper.getData(file).get(0).getLocation(), person.get(0).getLocation());
		assertEquals(personMapper.getData(file).get(0).getTimeZone(), person.get(0).getTimeZone());

		assertFalse(personMapper.getData(file).get(0).getFirstName() == person.get(1).getFirstName());
		assertFalse(personMapper.getData(file).get(0).getLastName() == person.get(1).getLastName());
		assertFalse(personMapper.getData(file).get(0).getId() == person.get(1).getId());
		assertFalse(personMapper.getData(file).get(0).getLocation() == person.get(1).getLocation());
		assertFalse(personMapper.getData(file).get(0).getTimeZone() == person.get(1).getTimeZone());

	}

}
