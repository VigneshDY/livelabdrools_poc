package com.livelabdrools.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Person;
import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.utility.TimeTracker;
import org.apache.log4j.Logger;

public class PersonMapper extends DataMapper{
	private static	final Logger log= Logger.getLogger(PersonMapper.class);
	@Override
	public List<Object> getData(List<String[]> header, List<String[]> data) {
		TimeTracker timeTracker=new TimeTracker();
		log.info("Start time for person mapping "+timeTracker.getStartTime() );
		List<Object> person=new ArrayList<Object> ();
		Map<String,Integer> map=new HashMap<String,Integer> ();
		int j=0;
		for(String[] head:header)
		{
			for(int i=0;i<head.length;i++)
			{
				map.put(head[i], j);
				j++;
			}
			
		}
		String header1[]=new String[j];
		int index=0;
		
		for(Map.Entry<String, Integer> m:map.entrySet())
		{
			header1[index]=m.getKey();
		}
		for(String[] data1:data)
		{
			for(int i=0;i<data1.length;i++)
			{
				Person person1=new Person();
				person1.setId(data1[map.get(header1[i])]);
				person1.setFirstName(data1[map.get(header1[i+1])]);
				person1.setLastName(data1[map.get(header1[i+2])]);
				person1.setLocation(data1[map.get(header1[i+3])]);
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
