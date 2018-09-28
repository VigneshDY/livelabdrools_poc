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

public class ExcelReaderTest {

	@Test
	public void testGetHeader() {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();
		ExcelReader excelReader=new ExcelReader();
		String arr[]=new String[2];
		String arr1[]=new String[2];
		arr[0]="location";
		arr[1]="timezone";
		arr1[0]="EQ";
		arr1[1]=null;
		headerList.add(arr);
		headerList.add(arr1);
		assertEquals(headerList.get(0),excelReader.getHeader(new File("C:\\Users\\674448\\Desktop\\SpringEL_Rule.xlsx"), 2).get(0));
		assertEquals(headerList.get(1),excelReader.getHeader(new File("C:\\Users\\674448\\Desktop\\SpringEL_Rule.xlsx"), 2).get(1));
	}
	
	@Test
	public void testGetData()
	{
		List<String[]> dataList = new ArrayList<String[]>();
		ExcelReader excelReader=new ExcelReader();
		String arr[]=new String[2];
		String arr1[]=new String[2];
		arr[0]="Chennai";
		arr[1]="IST";
		arr1[0]="Ind%";
		arr1[1]="IST";
		dataList.add(arr);
		dataList.add(arr1);
		assertEquals(dataList.get(0),excelReader.getData(new File("C:\\Users\\674448\\Desktop\\SpringEL_Rule.xlsx"), 2).get(0));
		assertEquals(dataList.get(1),excelReader.getData(new File("C:\\Users\\674448\\Desktop\\SpringEL_Rule.xlsx"), 2).get(1));
	}
}
