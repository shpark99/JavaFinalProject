package edu.handong.finalproject.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	
	public ArrayList<String> getData(InputStream is){
	ArrayList<String> values = new ArrayList<String>();
	
	try(InputStream inp = is){
		//InputStream inp = new FileInputStream("workbook.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(inp);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		int i =1;
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
//				System.out.println(i);
//				System.out.println(cell.getCellType());
//				if(i==55) System.out.println(cell.getStringCellValue());
//				i++;
				switch(cell.getCellType()) {
					case STRING: values.add(cell.getStringCellValue()); break;
					case NUMERIC: values.add(Double.toString(cell.getNumericCellValue())); break;
					case BLANK: values.add(""); break;
					default: break;
				}
//				values.add(cell.getStringCellValue());
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return values;
}
	
	
	
	
//	public ArrayList<String> getData(InputStream is){
//		ArrayList<String> values = new ArrayList<String>();
//		
//		try(InputStream inp = is){
//			//InputStream inp = new FileInputStream("workbook.xlsx");
//			Workbook wb = WorkbookFactory.create(inp);
//			Sheet sheet = wb.getSheetAt(0);
//			
//			Iterator<Row> rowIterator = sheet.iterator();
//			while(rowIterator.hasNext()) {
//				Row row = rowIterator.next();
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while(cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					values.add(cell.getStringCellValue());
//				}
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return values;
//	}
}




//public ArrayList<String> getData(String path){
//ArrayList<String> values = new ArrayList<String>();
//
//System.out.println(path);
//
//try(InputStream inp = new FileInputStream(path)){
//	//InputStream inp = new FileInputStream("workbook.xlsx");
//	Workbook wb = WorkbookFactory.create(inp);
//	Sheet sheet = wb.getSheetAt(0);
//	Row row = sheet.getRow(2);
//	Cell cell = row.getCell(1);
//	
//	if(cell == null) cell = row.createCell(3);
//	
//	values.add(cell.getStringCellValue());
//	
//	
//} catch (FileNotFoundException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//return values;
//}
