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
	List<Rule> listRule = new ArrayList<Rule>();
	List<Person> listPerson = new ArrayList<Person>();

	public void readExcel() {

		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		String attribute[] = new String[2];
		try {
			FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\690257\\Desktop\\SpringEL_Rule.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			int countAttribute = 0;
			String operator[] = new String[2];
			int countOperator = 0;
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					attribute[countAttribute] = currentCell.toString();
					countAttribute++;
				}
				break;

			}

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					operator[countOperator] = currentCell.toString();
					countOperator++;

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

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readPsv() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\690257\\Desktop\\personinput.psv"));
			String line = "";
			Person person = new Person();
			int index = 0;
			Integer length = 0;
			String count = "0";
			Map<String, String> map = new LinkedHashMap<String, String>();

			String[] temp = new String[5];
			while ((line = br.readLine()) != null) {

				if (index == 0) {
					String arr[] = line.split("\\|");
					person.setId(arr[0]);
					person.setFirstName(arr[1]);
					person.setLastName(arr[2]);
					person.setLocation(arr[3]);
					person.setTimeZone(arr[4]);
					temp[0] = person.getId();
					temp[1] = person.getFirstName();
					temp[2] = person.getLastName();
					temp[3] = person.getLocation();
					temp[4] = person.getTimeZone();
					for (int j = 0; j < arr.length; j++) {

						map.put(arr[j], count);
						length++;
						count = length.toString();

					}
					index++;
				} else {

					String arr1[] = line.split("\\|");
					Person person1 = new Person();
					// int k = 0;
					person1.setId(arr1[Integer.parseInt(map.get(temp[0]))]);
					person1.setFirstName(arr1[Integer.parseInt(map.get(temp[1]))]);
					person1.setLastName(arr1[Integer.parseInt(map.get(temp[2]))]);
					person1.setLocation(arr1[Integer.parseInt(map.get(temp[3]))]);
					person1.setTimeZone(null);
					listPerson.add(person1);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RuleEngine ruleEngine = new RuleEngine(listRule);
		ruleEngine.applyRule(listPerson);

	}
}
