package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class demo2 {

	public static void main(String[] args) throws IOException, Exception {
//		Workbook wb = new XSSFWorkbook(new FileInputStream(
//				new File("/Users/jayesh.hinge/git/demo/DemoTest/src/test/resources/testexcel.xlsx")));
//		
//		File excelFile = new File("/Users/jayesh.hinge/git/demo/DemoTest/src/test/resources/testexcel.xlsx");
//		InputStream inp = new FileInputStream(excelFile);
//		Workbook wb1= WorkbookFactory.create(inp);
//		FileOutputStream fos = new FileOutputStream(excelFile);
//		//Workbook wb1 = WorkbookFactory
//		//		.create(new File("/Users/jayesh.hinge/git/demo/DemoTest/src/test/resources/testexcel.xlsx"));
//		// Workbook wb1 = new XSSFWorkbook();
//		// new FileOutputStream(new
//		// File("/Users/jayesh.hinge/git/demo/DemoTest/src/test/resources/testexcel.xlsx"))
//
//		readExcelDetails(wb);
//		writeToExcelFile(wb1,fos);

		File file = new File("/Users/jayesh.hinge/eclipse-workspace/KeywordDriven/src/main/java/dataEngine/DataEngine.xlsx ");
		Workbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheet("DataEngine");
		int numberOfRows = sheet.getLastRowNum();
		int numberofCells = sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 0; i < numberOfRows ; i++) {
			for (int j = 0; j < numberofCells; j++) {
				System.out.print(sheet.getRow(i).getCell(j) + "\t");
			}
			System.out.println("");
		}
		
	}

	public static void writeToExcelFile(Workbook wb, FileOutputStream fos) throws FileNotFoundException {
		System.out.println("Writing details to excel sheet");
		String[] col = { "Roll_No", "Name", "Date Of Birth" };
		List<Student> student = new ArrayList<>();
		List<String> st = new ArrayList<>();
		st.add("Test 1");
		st.add("Test 3");
		st.add("Test 2");
		st.forEach(value -> {
		//	System.out.println("Value: " + value);
		});
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Sam");
		map.put(2, "Ram");
		map.forEach((key, value) -> {
		//	System.out.println("Key: " + key + "\tValue: " + value);
		});
		int count = map.keySet().size();
		//System.out.println("Map count" + count);
		//System.out.println("Total: " + (count + st.size()));
		for (int i = 0; i < st.size(); i++) {
			map.put(i + 3, st.get(i));
		}
		map.forEach((key, value) -> {
			//System.out.println("Key: " + key + "\tValue: " + value);
		});
		Student s = new Student();
		Calendar date = Calendar.getInstance();
		date.set(1992, 7, 15);
		s.setDateOfBirth(date.getTime());
		s.setRoll_No(1);
		s.setName("Shyam");
		student.add(s);
		Student s1 = new Student();
		s1.setRoll_No(2);
		s1.setName("Jony");
		date.set(1992, 7, 14);
		s1.setDateOfBirth(date.getTime());
		student.add(s1);
		student.forEach(value -> {
			//System.out.println(value.getRoll_No() + "\t" + value.getName() + "\t" + value.getDateOfBirth());// student.get(1).Name
		});
		Sheet sheet = null;
		boolean b=false;
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("Student Data")) {
				b=true;
				break;
			} 
		}
		if (b) {
			System.out.println("sheet already exist");
			sheet = wb.getSheet("Student Data");
		} else {
			System.out.println("Sheet is not present");
			sheet = wb.createSheet("Student Data");
		}
		Row row = sheet.createRow(0);
		for (int i = 0; i < col.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(col[i]);
		}
		int rowcount = 1;
		for (Student students : student) {
			Row row1 = sheet.createRow(rowcount++);
			row1.createCell(0).setCellValue(students.getRoll_No());
			row1.createCell(1).setCellValue(students.getName());
			Cell cell1 = row1.createCell(2);
			cell1.setCellValue(students.getDateOfBirth());

			CellStyle dateCellStyle = wb.createCellStyle();
			CreationHelper createHelper = wb.getCreationHelper();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
			cell1.setCellStyle(dateCellStyle);
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < col.length; i++) {
			sheet.autoSizeColumn(i);
		}

		try {
			wb.write(fos);
			fos.close();
			wb.close();
			System.out.println("Added details to sheet please check");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readExcelDetails(Workbook wb) throws Exception {

		System.out.println("Workbook contain number of sheets are: " + wb.getNumberOfSheets());
		// Case 1: using iterator
		// Iterator<Sheet> it = wb.sheetIterator();
		// while (it.hasNext()) {
		// System.out.println("Sheet Name is: " + it.next().getSheetName());
		// }
		// Case 2: using lambda expression
		wb.forEach(Sheet -> {
			System.out.println("Sheet Name: " + Sheet.getSheetName());
		});
		DataFormatter df = new DataFormatter();
		// get content of first sheet
		Sheet sheet = wb.getSheetAt(0);
		System.out.println("*************First Sheet:" + sheet.getSheetName() + " *************");
		sheet.forEach(Row -> {
			Row.forEach(Cell -> {
				// String value=df.formatCellValue(Cell);
				/// System.out.print(value+"\t");
				printCellContentsByType(Cell);
			});
			System.out.println("");
		});
		System.out.println("");
		// get content of second sheet
		Sheet sheet2 = wb.getSheetAt(1);
		System.out.println("*************Second Sheet:" + sheet2.getSheetName() + " *************");
		sheet2.forEach(Row -> {
			Row.forEach(Cell -> {
				// String value=df.formatCellValue(Cell);
				/// System.out.print(value+"\t");
				printCellContentsByType(Cell);
			});
			System.out.println("");
		});
	}

	public static void printCellContentsByType(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			System.out.print(cell.getBooleanCellValue());
			break;
		case ERROR:
			System.out.print(cell.getErrorCellValue());
			break;
		case FORMULA:
			System.out.print(cell.getCellFormula());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				System.out.print(cell.getDateCellValue());
			} else {
				System.out.print((int) cell.getNumericCellValue());
			}
			break;
		case STRING:
			System.out.print(cell.getRichStringCellValue().toString());
			break;
		case _NONE:
			System.out.println("");
			break;
		default:
			System.out.println("");
			break;
		}
		System.out.print("\t");
	}

}
