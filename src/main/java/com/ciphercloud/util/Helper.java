/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.ciphercloud.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ciphercloud.util.ExcelUtil;

public class Helper {
	ExcelUtil excelUtil = null;
	private DataFormatter formatter = new DataFormatter();

	public static String TEST_ID_STR = "Id";

	private Map<String, int[]> parserMap = new HashMap<String, int[]>();

	public List<ExcelUtil> parseExcelTestCases(Sheet sheet)
    throws InvalidFormatException, IOException
  {
		   
		   

	       List<ExcelUtil> testCases = new ArrayList<ExcelUtil>();
	       int headerRow = getHeaderRow(sheet);
	       int testIdCol = 0;
	       
	    /*    * parse the sheet starting from headerRow. Each row is a test case and
	        * needs to be added if test id is not blank*/
	        Row row = null;
	       for (int j = headerRow + 1; j <= sheet.getLastRowNum(); j++) {
	               row = sheet.getRow(j);
	               if (row != null) {
	                       if (!formatter.formatCellValue(row.getCell(testIdCol)).isEmpty()) 
	                       {
	                               ExcelUtil tc = null;
	                               try {
	                                       tc = getExcelTestCaseFromRow(row, formatter);
	                               } catch (Exception e) {
	                                       e.printStackTrace();
	                                       // skip the current row and continue
	                                       continue;
	                               }
	                               testCases.add(tc);
	                       }
	               }
	       }
	       return testCases;
}

	public int getHeaderRow(Sheet sheet) {
		for (int i = 0; i <= sheet.getLastRowNum(); ++i) {
			Row row = sheet.getRow(i);
			if (row != null) {
				String id = this.formatter.formatCellValue(row.getCell(0));
				if (TEST_ID_STR.equals(id)) {
					return i;
				}
			}
		}
		return 0;
	}

	public ExcelUtil getExcelTestCaseFromRow(Row row, DataFormatter formatter) {
		int testIdCol = getMapValue("testIdCol", 0);
		int testModuleCol = getMapValue("testModuleCol", 1);
		int testNameCol = getMapValue("testNameCol", 2);
		int testDescCol = getMapValue("testDescCol", 3);
		int testParamCol = getMapValue("testParamCol", 4);
		int testParamCol1 = getMapValue("testParamCol1", 5);
		int testParamCol2 = getMapValue("testParamCol2", 6);
		int testParamCol3 = getMapValue("testParamCol3", 7);
		int testParamCol4 = getMapValue("testParamCol4", 8);
		int testParamCol5 = getMapValue("testParamCol5", 9);
		int testParamCol6 = getMapValue("testParamCol6", 10);
		int testParamCol7 = getMapValue("testParamCol7", 11);
		int testParamCol8 = getMapValue("testParamCol8", 12);
		int testParamCol9 = getMapValue("testParamCol9", 13);
		int testParamCol10 = getMapValue("testParamCol10", 14);
		int testParamCol11 = getMapValue("testParamCol11", 15);
		int testParamCol12 = getMapValue("testParamCol12", 16);
		int testParamCol13 = getMapValue("testParamCol13", 17);
		int testParamCol14 = getMapValue("testParamCol14", 18);
		int testParamCol15 = getMapValue("testParamCol15", 19);
		int testParamCol16 = getMapValue("testParamCol16", 20);
		int testParamCol17 = getMapValue("testParamCol17", 21);
		int testParamCol18 = getMapValue("testParamCol18", 22);
		int testParamCol19 = getMapValue("testParamCol19", 23);
		int testParamCol20 = getMapValue("testParamCol20", 24);
		int testParamCol21 = getMapValue("testParamCol21", 25);
		int testParamCol22 = getMapValue("testParamCol22", 26);
		int testParamCol23 = getMapValue("testParamCol23", 27);
		int testParamCol24 = getMapValue("testParamCol24", 28);
		int testParamCol25 = getMapValue("testParamCol25", 29);
		int testParamCol26 = getMapValue("testParamCol26", 30);
		int testParamCol27 = getMapValue("testParamCol27", 31);
		int testParamCol28 = getMapValue("testParamCol28", 32);
		int testParamCol29 = getMapValue("testParamCol29", 33);
		int testParamCol30 = getMapValue("testParamCol30", 34);
		int testParamCol31 = getMapValue("testParamCol31", 35);
		int testParamCol32 = getMapValue("testParamCol32", 36);
		int testParamCol33 = getMapValue("testParamCol33", 37);
		int testParamCol34 = getMapValue("testParamCol34", 38);

		return (this.excelUtil = new ExcelUtil(formatter.formatCellValue(row
				.getCell(testIdCol)), formatter.formatCellValue(row
				.getCell(testModuleCol)), formatter.formatCellValue(row
				.getCell(testNameCol)), formatter.formatCellValue(row
				.getCell(testDescCol)), formatter.formatCellValue(row
				.getCell(testParamCol)), formatter.formatCellValue(row
				.getCell(testParamCol1)), formatter.formatCellValue(row
				.getCell(testParamCol2)), formatter.formatCellValue(row
				.getCell(testParamCol3)), formatter.formatCellValue(row
				.getCell(testParamCol4)), formatter.formatCellValue(row
				.getCell(testParamCol5)), formatter.formatCellValue(row
				.getCell(testParamCol6)), formatter.formatCellValue(row
				.getCell(testParamCol7)), formatter.formatCellValue(row
				.getCell(testParamCol8)), formatter.formatCellValue(row
				.getCell(testParamCol9)), formatter.formatCellValue(row
				.getCell(testParamCol10)), formatter.formatCellValue(row
				.getCell(testParamCol11)), formatter.formatCellValue(row
				.getCell(testParamCol12)), formatter.formatCellValue(row
				.getCell(testParamCol13)), formatter.formatCellValue(row
				.getCell(testParamCol14)), formatter.formatCellValue(row
				.getCell(testParamCol15)), formatter.formatCellValue(row
				.getCell(testParamCol16)), formatter.formatCellValue(row
				.getCell(testParamCol17)), formatter.formatCellValue(row
				.getCell(testParamCol18)), formatter.formatCellValue(row
				.getCell(testParamCol19)), formatter.formatCellValue(row
				.getCell(testParamCol20)), formatter.formatCellValue(row
				.getCell(testParamCol21)), formatter.formatCellValue(row
				.getCell(testParamCol22)), formatter.formatCellValue(row
				.getCell(testParamCol23)), formatter.formatCellValue(row
				.getCell(testParamCol24)), formatter.formatCellValue(row
				.getCell(testParamCol25)), formatter.formatCellValue(row
				.getCell(testParamCol26)), formatter.formatCellValue(row
				.getCell(testParamCol27)), formatter.formatCellValue(row
				.getCell(testParamCol28)), formatter.formatCellValue(row
				.getCell(testParamCol29)), formatter.formatCellValue(row
				.getCell(testParamCol30)), formatter.formatCellValue(row
				.getCell(testParamCol31)), formatter.formatCellValue(row
				.getCell(testParamCol32)), formatter.formatCellValue(row
				.getCell(testParamCol33)), formatter.formatCellValue(row
				.getCell(testParamCol34))));
	}

	private int getMapValue(String key, int defaultVal) {
		int[] value = (int[]) this.parserMap.get(key);
		return ((value != null) ? value[0] : defaultVal);
	}
}