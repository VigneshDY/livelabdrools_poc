package com.livelabdrools.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Data;
import com.livelabdrools.model.Person;
import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.reader.ReadFile;
import com.livelabdrools.utility.TimeTracker;
import org.apache.log4j.Logger;

public class PersonMapper extends DataMapper{

	private static	final Logger log= Logger.getLogger(PersonMapper.class);

	@Override
	public List<Person> getData(File inputFile) {
		TimeTracker timeTracker=new TimeTracker();
		String[] fileParts = inputFile.getName().split("\\.");
		String ext = fileParts[fileParts.length-1];
		ReadFile fileReader = fileReaders.get(ext);
		Data data = fileReader.readFile(inputFile,1);
		List<String[]> headerList=data.getHeader();
		List<String[]> dataList =data.getData();

		log.info("Start time for person mapping "+timeTracker.getStartTime() );
		List<Person> person=new ArrayList<Person> ();

		for(String[] data1:dataList)
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
		timeTracker.setEndTime();
		log.info("End time for person mapping "+timeTracker.getEndTime());
		log.info("Total time for person mapper is "+timeTracker.getTotalTimeElapsed());
          return person;
				
			}


}
