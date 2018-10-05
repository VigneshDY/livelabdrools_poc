package com.livelabdrools.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class TimeTracker {

	private Long startTime;
	
	private final SimpleDateFormat SIMPLE_DATE_FORMAT_SS = new SimpleDateFormat("SS");
	
	private final SimpleDateFormat SIMPLE_DATE_FORMAT_HH_MM_SS_SS = new SimpleDateFormat("HH:mm:ss.SS");
	
	private final Logger log = Logger.getLogger(TimeTracker.class);

	public TimeTracker() {
		this.startTime = System.currentTimeMillis();
	}
	
	public String getProcessTime() {
		return millsToTime(Long.toString(System.currentTimeMillis() - startTime));
	}
	
	public String millsToTime(String millTime) {
		String time = "";
		try{
			time=SIMPLE_DATE_FORMAT_HH_MM_SS_SS.format(SIMPLE_DATE_FORMAT_SS.parse(millTime));
		} catch (ParseException parseException) {
			log.error("Unable to parse the time "+millTime);
		}
		return time;
	}
}
