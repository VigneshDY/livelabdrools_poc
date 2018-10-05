package com.livelabdrools.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.livelabdrools.model.Data;
import com.livelabdrools.model.Person;
import com.livelabdrools.reader.ReadFile;
import com.livelabdrools.utility.TimeTracker;

@Component
@Qualifier(value="personMapper")
public class PersonMapper extends DataMapper {

	private static final Logger log = Logger.getLogger(PersonMapper.class);

	@Override
	public List<Person> getData(File inputFile) {
		TimeTracker timeTracker = new TimeTracker();
		String[] fileParts = inputFile.getName().split("\\.");
		String ext = fileParts[fileParts.length - 1];
		ReadFile fileReader = fileReaders.get(ext);
		Data data = fileReader.readFile(inputFile, 1);
		List<String[]> headerList = data.getHeader();

		Map<String, Integer> headerMap = new LinkedHashMap<String, Integer>();
		for (String[] header : headerList) {
			for (int i = 0; i < header.length; i++) {
				headerMap.put(header[i].toLowerCase(), i);
			}
		}

		List<String[]> dataList = data.getData();
		List<Person> person = new ArrayList<Person>();
		for (String[] data1 : dataList) {
			Person person1 = new Person();
			person1.setId(data1[headerMap.get("id")]);
			person1.setFirstName(data1[headerMap.get("firstname")]);
			person1.setLastName(data1[headerMap.get("lastname")]);
			person1.setLocation(data1[headerMap.get("location")]);
			person.add(person1);
		}

		log.info("Total time for person mapper is " + timeTracker.getProcessTime());

		return person;
	}
}
