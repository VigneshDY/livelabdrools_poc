package com.livelabdrools.reader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.model.Data;
import com.livelabdrools.utility.TimeTracker;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelReader implements ReadFile {
	@Autowired
	protected DataMapper dataMapper;
	private static final Logger log = Logger.getLogger(ExcelReader.class);

	public Data readFile(File fileToRead, int noOfHeaders) {
		TimeTracker timeTracker = new TimeTracker();
		Data data = new Data();
		try {

			String startTime = timeTracker.getStartTime().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("SS");
			Date date = sdf.parse(startTime);
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss.SS");
			log.info("Start time for reading excel " + sdf1.format(date));
			data.setHeader(this.getHeader(fileToRead, noOfHeaders));
			data.setData(this.getData(fileToRead, noOfHeaders));
			timeTracker.setEndTime();
			String endTime = timeTracker.getEndTime().toString();
			Date date1 = sdf.parse(endTime);
			log.info("End time for reading excel " + sdf1.format(date1));
			String totalTime = timeTracker.getTotalTimeElapsed().toString();
			Date date2 = sdf.parse(totalTime);
			log.info("Total time for reading excel file " + sdf1.format(date2));
		} catch (Exception e) {
			e.getMessage();
		}
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
				if (null != excelFile)
					excelFile.close();
			} catch (IOException e) {
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
			for (int i = 0; i < noOfColumns; i++, iterator.next())
				;
			while ((iterator.hasNext())) {
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
				if (null != excelFile)
					excelFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}
}