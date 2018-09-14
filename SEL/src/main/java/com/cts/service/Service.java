package com.cts.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

import com.cts.model.Person;
import com.cts.model.Rule;
import com.cts.operator.Operator;

public class Service {

	RuleEngine ruleEngine;

	public void readExcel() {

		List<Rule> listRule = new ArrayList<Rule>();
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		String attribute[] = new String[2];
		try {
			FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\690257\\Desktop\\SpringEL_Rule.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			String t = "";

			int count = 0;
			String operator[] = new String[2];
			int count1 = 0;
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					attribute[count] = currentCell.toString();
					count++;
				}
				break;

			}

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					operator[count1] = currentCell.toString();
					count1++;

				}
				break;

			}

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				int cellIndex = 0;
				List<RuleFact> ruleFactList = new ArrayList<RuleFact>();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					// t = t + currentCell.toString() + " ";
					//
					RuleFact ruleFact = new RuleFact();
					ruleFact.setAttribute(attribute[cellIndex]);
					ruleFact.setOperator(operator[cellIndex]);
					ruleFact.setValue(currentCell.toString());
					++cellIndex;
					ruleFactList.add(ruleFact);
				}

				Rule rule = new Rule(ruleFactList);
				listRule.add(rule);
			}
			ruleEngine = new RuleEngine(listRule);

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readPsv() {
		BufferedReader br;
		List<Person> listPerson = new ArrayList<Person>();

		try {
			br = new BufferedReader(new FileReader("C:\\Users\\690257\\Desktop\\personinput.psv"));

			String line = "";
			Person p = new Person();
			int i = 0;
			Integer l = 0;
			String count = "0";
			Map<String, String> map = new LinkedHashMap<String, String>();

			String[] temp = new String[5];

			while ((line = br.readLine()) != null) {

				if (i == 0) {
					String arr[] = line.split("\\|");
					p.setId(arr[0]);
					p.setFirstName(arr[1]);
					p.setLastName(arr[2]);
					p.setLocation(arr[3]);
					p.setTimeZone(arr[4]);
					temp[0] = p.getId();
					temp[1] = p.getFirstName();
					temp[2] = p.getLastName();
					temp[3] = p.getLocation();
					temp[4] = p.getTimeZone();
					for (int j = 0; j < arr.length; j++) {

						map.put(arr[j], count);
						l++;
						count = l.toString();

					}
					i++;
				} else {

					String arr1[] = line.split("\\|");
					Person p1 = new Person();
					int k = 0;
					p1.setId(arr1[Integer.parseInt(map.get(temp[k]))]);
					p1.setFirstName(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
					p1.setLastName(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
					p1.setLocation(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
					p1.setTimeZone(null);
					listPerson.add(p1);

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(listPerson);

		ruleEngine.applyRule(listPerson);

	}

}
