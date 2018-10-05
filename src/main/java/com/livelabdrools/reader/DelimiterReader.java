package com.livelabdrools.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

	private List<String[]> getHeader(File fileToRead, int noOfHeaderRows) {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();
		FileReader inputFileReader = null;
		BufferedReader inputFileReaderBuffer = null;
		try {
			inputFileReader = new FileReader(fileToRead);
			inputFileReaderBuffer = new BufferedReader(inputFileReader);
			String line = "";
			while ((line = inputFileReaderBuffer.readLine()) != null && index < noOfHeaderRows) {
				index++;
				headerList.add(line.split(DELIMITOR));
			}
		} catch (FileNotFoundException fileNotFound) {
			log.error("File was missing : ", fileNotFound);
		} catch (IOException ioException) {
			log.error("Exception occurred while closin the file reader", ioException);
		} finally {
			if(null != inputFileReader) {
				try {
					inputFileReader.close();
				} catch (IOException ioException) {
					log.error("Exception occurred while closin the file reader", ioException);
				}
			}
			if(null != inputFileReaderBuffer) {
				try {
				inputFileReaderBuffer.close();
				} catch(IOException ioException) {
					log.error("Exception occurred while closin the file reader",ioException);
					
				}
			}
		}
		return headerList;
	}

	public List<String[]> getData(File fileToRead, int noOfHeaders) {
		List<String[]> data = new ArrayList<String[]>();
		FileReader inputFileReader = null;
		BufferedReader inputFileReaderBuffer = null;
		try {
			inputFileReader = new FileReader(fileToRead);
			inputFileReaderBuffer = new BufferedReader(inputFileReader);
			String line = "";
			for (int i = 0; i < noOfHeaders; i++, inputFileReaderBuffer.readLine());
			while ((line = inputFileReaderBuffer.readLine()) != null) {
				data.add(line.split(DELIMITOR));
			}
		} catch (FileNotFoundException fileNotFound) {
			log.error("File was missing : ", fileNotFound);
		} catch (IOException ioException) {
			log.error("Exception occurred while closin the file reader", ioException);
		} finally {
			if(null != inputFileReader) {
				try {
					inputFileReader.close();
				} catch (IOException ioException) {
					log.error("Exception occurred while closin the file reader", ioException);
				}
			}
			if(null != inputFileReaderBuffer) {
				try {
				inputFileReaderBuffer.close();
				} catch(IOException ioException) {
					log.error("Exception occurred while closin the file reader",ioException);
					
				}
			}
		}
		return data;
	}

	public Data readFile(File fileToRead, int noOfHeaders) {
		TimeTracker timeTracker = new TimeTracker();
		Data data = new Data();
		
		data.setHeader(this.getHeader(fileToRead, noOfHeaders));
		data.setData(this.getData(fileToRead, noOfHeaders));
		
		log.info("Total time for reading delimited file " + timeTracker.getProcessTime());
		
		return data;
	}
}
