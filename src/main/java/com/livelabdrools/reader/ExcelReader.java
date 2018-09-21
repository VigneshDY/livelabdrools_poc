package com.livelabdrools.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.livelabdrools.model.Rule;
import com.livelabdrools.model.RuleFact;

public class ExcelReader implements ReadFile {
	private static String excel = "C:\\Users\\674448\\Desktop\\SpringEL_Rule.xlsx";
	List<Rule> listRule = new ArrayList<Rule>();
	
	public String getHeader() {
		String input = "";
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		try {
			FileInputStream excelFile = new FileInputStream(new File(excel));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int noOfColumns = datatypeSheet.getRow(0).getPhysicalNumberOfCells();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					input = input + " " + currentCell;

				}
				break;
			}

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					input = input + "-" + currentCell;
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return input;
	}

	public String getData() {
		String input = "";
		try {
			FileInputStream excelFile = new FileInputStream(new File("excel"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			iterator.next();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int cellIndex = 0;
				List<RuleFact> ruleFactList = new ArrayList<RuleFact>();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					input = input + " " + currentCell.toString();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}

}
