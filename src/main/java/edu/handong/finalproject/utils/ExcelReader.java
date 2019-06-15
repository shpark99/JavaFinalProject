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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public ArrayList<String> getData(InputStream is){
		ArrayList<String> values = new ArrayList<String>();
		
		try(InputStream inp = is){
			//InputStream inp = new FileInputStream("workbook.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					values.add(cell.getStringCellValue());
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
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
