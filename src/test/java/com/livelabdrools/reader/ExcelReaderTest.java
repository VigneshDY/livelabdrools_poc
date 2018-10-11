package com.livelabdrools.reader;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.livelabdrools.mapper.RuleMapperTest;

public class ExcelReaderTest {
	String name = "SpringEL_Rule.xlsx";
	ClassLoader classLoader = new ExcelReaderTest().getClass().getClassLoader();
	File file = new File(classLoader.getResource(name).getFile());

	@Test
	public void testGetHeader() {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();
		ExcelReader excelReader = new ExcelReader();
		String arr[] = {"location","timezone"};
		String arr1[] = {"EQ",null};
		String arr2[] ={"abcd","defg"};
		String arr3[] = {"ghij","bnhf"};
		headerList.add(arr);
		headerList.add(arr1);
		headerList.add(arr2);
		headerList.add(arr3);
		assertEquals(headerList.get(0), excelReader.getHeader(file, 2).get(0));
		assertEquals(headerList.get(1), excelReader.getHeader(file, 2).get(1));
		assertFalse(headerList.get(2).equals(excelReader.getHeader(file, 2).get(0)));
		assertFalse(headerList.get(3).equals(excelReader.getHeader(file, 2).get(1)));
	}

	@Test
	public void testGetData() {
		List<String[]> dataList = new ArrayList<String[]>();
		ExcelReader excelReader = new ExcelReader();
		String arr[] = {"Chennai","IST"};
		String arr1[] = {"Ind%","IST"};
		String arr2[] = {"New Delhi","IST"};
		String arr3[] = {"Mumbai","IST"};
		dataList.add(arr);
		dataList.add(arr1);
		dataList.add(arr2);
		dataList.add(arr3);
		assertEquals(dataList.get(0), excelReader.getData(file, 2).get(0));
		assertEquals(dataList.get(1), excelReader.getData(file, 2).get(1));
		assertFalse(dataList.get(0).equals(excelReader.getData(file, 2).get(0)));
		assertFalse(dataList.get(1).equals(excelReader.getData(file, 2).get(1)));
		assertFalse(dataList.get(2).equals(excelReader.getData(file, 2).get(2)));
		assertFalse(dataList.get(3).equals(excelReader.getData(file, 2).get(3)));
	}
}
