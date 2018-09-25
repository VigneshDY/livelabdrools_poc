package com.livelabdrools.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.model.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelReader implements ReadFile {
	@Autowired
	protected DataMapper dataMapper;
	public Data readFile(File fileToRead, int noOfHeaders) {
		Data data = new Data();
		data.setHeader(this.getHeader(fileToRead, noOfHeaders));
		data.setData(this.getData(fileToRead, noOfHeaders));
		return data;
	}

	public List<String[]> getHeader(File fileToRead, int noOfHeaders) {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();
		FileInputStream excelFile = null;
		try {
			String input = "";
			excelFile = new FileInputStream(fileToRead);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int noOfColumns = datatypeSheet.getRow(0).getPhysicalNumberOfCells();
			while ((iterator.hasNext()) && index < noOfHeaders) {
				String[] data = new String[noOfColumns];
				index++;
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int colPosition = 0;
				while (cellIterator.hasNext()) {
					data[colPosition] = cellIterator.next().getStringCellValue();
					++colPosition;
				}
				headerList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != excelFile)
					excelFile.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
			return headerList;
	}

	public List<String[]> getData(File fileToRead, int noOfHeaders) {
		int index = 0;
		List<String[]> dataList = new ArrayList<String[]>();
		FileInputStream excelFile = null;
		try {
			String input = "";
			excelFile = new FileInputStream(fileToRead);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int noOfColumns = datatypeSheet.getRow(0).getPhysicalNumberOfCells();
			while ((iterator.hasNext()) && index >= noOfHeaders) {
				String[] data = new String[noOfColumns];
				index++;
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int colPosition = 0;
				while (cellIterator.hasNext()) {
					data[colPosition] = cellIterator.next().getStringCellValue();
					++colPosition;
				}
				dataList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != excelFile)
					excelFile.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		return dataList;
	}
}