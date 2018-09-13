package com.cts.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import com.cts.model.Rule;
import com.cts.operator.Operator;

public class Service {
	public void readExcel() {

		List<Rule> listRule = new ArrayList<Rule>();
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		String attribute[] = new String[2];
		try {
			FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\672845\\Desktop\\SpringEL_Rule.xlsx"));
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

			while(iterator.hasNext()) {

				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				int cellIndex = 0;
				List<RuleFact> ruleFactList = new ArrayList<RuleFact>();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
//					t = t + currentCell.toString() + " ";
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
		
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Rule rf : listRule) {
			System.out.println(rf);
		}
		
		Rule rule=new Rule();
		List<RuleFact> inputList = rule.parseInputRuleFact();
		List<RuleFact> outputList = rule.parseOutputRuleFact();
		RuleEngine re=new RuleEngine();
		re.parseRulesList(inputList, outputList);

	}
	
	
	
}
