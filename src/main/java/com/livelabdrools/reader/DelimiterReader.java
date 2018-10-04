package com.livelabdrools.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.livelabdrools.model.Data;
import com.livelabdrools.utility.TimeTracker;

public class DelimiterReader implements ReadFile {
	private static final Logger log = Logger.getLogger(DelimiterReader.class);
	private final String DELIMITOR;

	public DelimiterReader(String delimiter) {
		this.DELIMITOR = delimiter;
	}

	public List<String[]> getHeader(File fileToRead, int noOfHeaderRows) {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			String line = "";
			while ((line = br.readLine()) != null && index < noOfHeaderRows) {
				index++;
				headerList.add(line.split(DELIMITOR));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headerList;
	}

	public List<String[]> getData(File fileToRead, int noOfHeaders) {
		int index = noOfHeaders;
		List<String[]> data = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			String line = "";
			for (int i = 0; i < noOfHeaders; i++, br.readLine())
				;
			while ((line = br.readLine()) != null) {
				index++;
				data.add(line.split(DELIMITOR));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Data readFile(File fileToRead, int noOfHeaders) {
		TimeTracker timeTracker = new TimeTracker();
		Data data = new Data();
		try {

			String startTime = timeTracker.getStartTime().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("SS");
			Date date = sdf.parse(startTime);
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss.SS");
			log.info("Start time for reading delimiter " + sdf1.format(date));
			data.setHeader(this.getHeader(fileToRead, noOfHeaders));
			data.setData(this.getData(fileToRead, noOfHeaders));
			timeTracker.setEndTime();
			String endTime = timeTracker.getEndTime().toString();
			Date date1 = sdf.parse(endTime);
			log.info("End time for reading delimiter " + sdf1.format(date1));
			String totalTime = timeTracker.getTotalTimeElapsed().toString();
			Date date2 = sdf.parse(totalTime);
			log.info("Total time for reading delimited file " + sdf1.format(date2));
		} catch (Exception e) {
			e.getMessage();
		}
		return data;
	}
}
