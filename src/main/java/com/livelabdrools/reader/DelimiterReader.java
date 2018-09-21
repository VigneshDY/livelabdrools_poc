package com.livelabdrools.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.model.Data;
import com.sun.javafx.binding.StringConstant;
import org.apache.poi.ss.formula.functions.T;

import com.livelabdrools.mapper.PersonMapper;
import com.livelabdrools.model.Person;

import javax.swing.text.StringContent;


public class DelimiterReader implements ReadFile{

	private final String DELIMITOR;

	public DelimiterReader (String delimiter) {
		this.DELIMITOR = delimiter;
	}

	private List<String[]> getHeader(File fileToRead, int noOfHeaders) {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			String line = "";
			while ((line = br.readLine()) != null && index < noOfHeaders) {
				index++;
				headerList.add(line.split(DELIMITOR));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headerList;
	}

	private List<String[]> getData(File fileToRead, int noOfHeaders) {
		int index = 0;
		List<String[]> data = new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			String line = "";
			while ((line = br.readLine()) != null && index >= noOfHeaders) {
				index++;
				data.add(line.split(DELIMITOR));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
}

	public Data readFile(File fileToRead, int noOfHeaders) {
		Data data = new Data();

		data.setHeader(this.getHeader(fileToRead, noOfHeaders));
		data.setHeader(this.getData(fileToRead, noOfHeaders));

		return data;
	}

}
