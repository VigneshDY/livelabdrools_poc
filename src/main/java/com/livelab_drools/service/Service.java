package com.livelab_drools.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.livelab_drools.model.Person;

import utility.TimeTracker;

@Component
public class Service {
	List<Person> listPerson = new ArrayList<Person>();

	public List<Person> readPsv() throws NumberFormatException, IOException {
		TimeTracker timeTracker = new TimeTracker();
		System.out.println(timeTracker.getStartTime());
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\690257\\Desktop\\personinput.psv"));
		String line = "";

		int index = 0;
		int headerPosition = 0;

		Map<String, Integer> headerMap = new LinkedHashMap<String, Integer>();

		String[] temp = new String[5];
		while ((line = br.readLine()) != null) {
			Person person = new Person();
			if (index == 0) {
				String arr[] = line.split("\\|");
				person.setId(arr[0]);
				person.setFirstName(arr[1]);
				person.setLastName(arr[2]);
				person.setLocation(arr[3]);
				person.setTimeZone(arr[4]);
				temp[0] = person.getId();
				temp[1] = person.getFirstName();
				temp[2] = person.getLastName();
				temp[3] = person.getLocation();
				temp[4] = person.getTimeZone();
				for (int j = 0; j < arr.length; j++) {

					headerMap.put(arr[j], headerPosition);
					headerPosition++;

				}
				index++;
			} else {

				String arr1[] = line.split("\\|");
				Person person1 = new Person();
				person1.setId(arr1[headerMap.get(temp[0])]);
				person1.setFirstName(arr1[headerMap.get(temp[1])]);
				person1.setLastName(arr1[headerMap.get(temp[2])]);
				person1.setLocation(arr1[headerMap.get(temp[3])]);
				person1.setTimeZone(null);
				listPerson.add(person1);

			}

		}
		timeTracker.setEndTime();
		System.out.println(timeTracker.getEndTime());
		System.out.println(timeTracker.totalTimeElapsed());
		return listPerson;

	}
}
