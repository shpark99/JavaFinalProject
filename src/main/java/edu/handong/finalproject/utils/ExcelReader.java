package edu.handong.finalproject.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public ArrayList<StringLinkedList> getData(InputStream is){
		ArrayList<StringLinkedList> Excels = new ArrayList<StringLinkedList>();
		
		try(InputStream inp = is){
	
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			XSSFSheet sheet = wb.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				StringLinkedList values = new StringLinkedList();
				
				for(int i = 0; i<row.getLastCellNum();i++) {
					Cell cell = row.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
						switch(cell.getCellType()) {
						  case STRING: values.insertNodeAfterCurrent(cell.getStringCellValue());break;
						  case NUMERIC:values.insertNodeAfterCurrent(Integer.toString((int)cell.getNumericCellValue()));break;
						  case BLANK: values.insertNodeAfterCurrent("");break;
						  default: System.out.println(cell.getCellType());break;
						}
				}
				Excels.add(values);
			}
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return Excels;
	}	
}
