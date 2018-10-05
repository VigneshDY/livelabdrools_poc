package com.livelabdrools.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.model.Data;
import com.livelabdrools.utility.TimeTracker;

public class ExcelReader implements ReadFile {
	@Autowired
	protected DataMapper dataMapper;
	private static final Logger log = Logger.getLogger(ExcelReader.class);

	public Data readFile(File fileToRead, int noOfHeaders) {
		TimeTracker timeTracker = new TimeTracker();
		Data data = new Data();
		try {
			data.setHeader(this.getHeader(fileToRead, noOfHeaders));
			data.setData(this.getData(fileToRead, noOfHeaders));

		} catch (Exception e) {
			e.getMessage();
		}
		log.info("Total time for reading excel file " + timeTracker.getProcessTime());
		return data;
	}

	public List<String[]> getHeader(File fileToRead, int noOfHeaders) {
		int index = 0;
		List<String[]> headerList = new ArrayList<String[]>();
		FileInputStream excelFile = null;
		Workbook workbook = null;
		try {
			String input = "";
			excelFile = new FileInputStream(fileToRead);
			workbook = new XSSFWorkbook(excelFile);
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
		} catch (FileNotFoundException fileNotFound) {
			log.error("File was Missing ", fileNotFound);
		} catch (IOException ioException) {
			log.error("IOException has occurred ", ioException);
		} finally {
			try {
				if (null != excelFile)
					excelFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null != workbook) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return headerList;
	}

	public List<String[]> getData(File fileToRead, int noOfHeaders) {
		List<String[]> dataList = new ArrayList<String[]>();
		FileInputStream excelFile = null;
		Workbook workbook = null;
		try {
			excelFile = new FileInputStream(fileToRead);
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int noOfColumns = datatypeSheet.getRow(0).getPhysicalNumberOfCells();
			for (int i = 0; i < noOfColumns; i++, iterator.next());
			while ((iterator.hasNext())) {
				String[] data = new String[noOfColumns];
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int colPosition = 0;
				while (cellIterator.hasNext()) {
					data[colPosition] = cellIterator.next().getStringCellValue();
					++colPosition;
				}
				dataList.add(data);
			}
		} catch (FileNotFoundException fileNotFound) {
			log.error("File was Missing ", fileNotFound);
		} catch (IOException ioException) {
			log.error("IOException has occurred ", ioException);
		} finally {
			try {
				if (null != excelFile)
					excelFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null != workbook) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}
}