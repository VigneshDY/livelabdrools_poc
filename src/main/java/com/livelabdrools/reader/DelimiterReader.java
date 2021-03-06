package com.livelabdrools.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Data;
import com.livelabdrools.utility.TimeTracker;
import com.sun.javafx.binding.StringConstant;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.model.Person;

import javax.swing.text.StringContent;


public class DelimiterReader implements ReadFile{


	private static	final Logger log= Logger.getLogger(DelimiterReader.class);
	private final String DELIMITOR;


	public DelimiterReader (String delimiter) {
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
			for(int i = 0; i < noOfHeaders; i++, br.readLine());
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
		TimeTracker timeTracker =new TimeTracker();
		log.info("Start time for reading delimiter "+timeTracker.getStartTime() );
		Data data = new Data();

		data.setHeader(this.getHeader(fileToRead, noOfHeaders));
		data.setData(this.getData(fileToRead, noOfHeaders));
          timeTracker.setEndTime();
		log.info("End time for reading delimiter "+timeTracker.getEndTime() );
         log.info("Total time for reading delimited file "+timeTracker.getTotalTimeElapsed());
		return data;
	}

}
