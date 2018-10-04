package com.livelabdrools.mapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Data;
import com.livelabdrools.model.Person;
import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.reader.ReadFile;
import com.livelabdrools.utility.TimeTracker;
import org.apache.log4j.Logger;

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
		List<String[]> dataList = data.getData();
		SimpleDateFormat sdf = new SimpleDateFormat("SS");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss.SS");
		try {
			String startTime = timeTracker.getStartTime().toString();

			Date date = sdf.parse(startTime);

			log.info("Start time for person mapping " + sdf1.format(date));

		} catch (Exception e) {
			e.getMessage();
		}
		List<Person> person = new ArrayList<Person>();
		for (String[] data1 : dataList) {
			for (int i = 0; i < data1.length; i++) {
				Person person1 = new Person();
				person1.setId(data1[i]);
				person1.setFirstName(data1[i + 1]);
				person1.setLastName(data1[i + 2]);
				person1.setLocation(data1[i + 3]);
				person1.setTimeZone(null);
				person.add(person1);
				break;
			}
		}
		timeTracker.setEndTime();
		try {
			String endTime = timeTracker.getEndTime().toString();
			Date date1 = sdf.parse(endTime);
			log.info("End time for person mapping " + sdf1.format(date1));
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String totalTime = timeTracker.getTotalTimeElapsed().toString();
			Date date2 = sdf.parse(totalTime);
			log.info("Total time for person mapper is " + sdf1.format(date2));
		} catch (Exception e) {
			e.getMessage();
		}

		return person;
	}
}
