package com.livelabdrools.mapper;


import com.livelabdrools.model.Person;
import org.junit.Test;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonMapperTest {

	@Test
	public void testGetData() {
		// fail("Not yet implemented");
		File file = new File("C:\\Users\\690257\\Desktop\\personinput.psv");
		PersonMapper personMapper = new PersonMapper();
		List<Person> person = new ArrayList<Person>();
		String arr[] = new String[5];
		arr[0] = "1";
		arr[1] = "Sarath";
		arr[2] = "ranganathan";
		arr[3] = "Chennai";
		arr[4] = null;
		Person per = new Person();
		per.setId(arr[0]);
		per.setFirstName(arr[1]);
		per.setLastName(arr[2]);
		per.setLocation(arr[3]);
		per.setTimeZone(arr[4]);
		person.add(per);
		assertEquals(person.get(0).getFirstName(), personMapper.getData(file).get(0).getFirstName());
		assertEquals(person.get(0).getLastName(), personMapper.getData(file).get(0).getLastName());
		assertEquals(person.get(0).getId(), personMapper.getData(file).get(0).getId());
		assertEquals(person.get(0).getLocation(), personMapper.getData(file).get(0).getLocation());
		assertEquals(person.get(0).getTimeZone(), personMapper.getData(file).get(0).getTimeZone());

	}

}