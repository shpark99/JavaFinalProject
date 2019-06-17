package edu.handong.finalproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.handong.finalproject.datamodel.ExelFile;

public class ExcelWriter {
	
	public void write1(String output) throws IOException {
		String name[] = output.split("\\.");
		String nameToSave = name[0] + "1." + name[1];
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Data1");
		
			Row row = sheet.createRow(0);
			
			for(int i=0;i<8;i++) {
				Cell cell = row.createCell(i);
				
				if(i==0) cell.setCellValue("�̸�");
				else if(i==1) cell.setCellValue("����");
				else if(i==2) cell.setCellValue("��๮ (300�� ����)");
				else if(i==3) cell.setCellValue("�ٽɾ�(keyword,��ǥ�� ����)");
				else if(i==4) cell.setCellValue("��ȸ��¥");
				else if(i==5) cell.setCellValue("�����ڷ���ȸ��ó (���ڷḵũ)");
				else if(i==6) cell.setCellValue("����ó (����� ��)");
				else if(i==7) cell.setCellValue("������(Copyright ����ó)");
			}
		
		File file = new File(nameToSave);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			nameToSave = file.getAbsolutePath();
			}
		
		FileOutputStream fs = new FileOutputStream(nameToSave);
		wb.write(fs);
		fs.close();
		//System.exit(0);
	}
	
	public void update1(String studentId,ArrayList<String> Exels, String output) throws IOException {
			
			String name[] = output.split("\\.");
			String nameToSave = name[0] + "1." + name[1];
			FileInputStream input = new FileInputStream(new File(nameToSave));
			Workbook wb = WorkbookFactory.create(input);
			Sheet sheet = wb.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			
			for(int i=7;i<Exels.size();i++) {
				Row row = sheet.createRow(++rowCount);
				
				for(int j=0;j<8;j++) {
					Cell cell = row.createCell(j);
					if(j==0) cell.setCellValue(studentId);
					else {
						cell.setCellValue(Exels.get(i));
						i++;
						if(i==Exels.size()) break;
					}
				}
				i--;
			}
			input.close();
	        FileOutputStream outputStream = new FileOutputStream(nameToSave);
	        wb.write(outputStream);
	        wb.close();
	        outputStream.close();
		}
	
	public void write2(String output) throws IOException {
		
		String name[] = output.split("\\.");
		String nameToSave = name[0] + "2." + name[1];
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Data2");
		
			Row row = sheet.createRow(0);
			
			for(int i=0;i<8;i++) {
				Cell cell = row.createCell(i);
				
				if(i==0) cell.setCellValue("����(�ݵ�� ��๮ ��Ŀ� �Է��� ����� ���ƾ� ��.)");
				else if(i==1) cell.setCellValue("ǥ/�׸� �Ϸù�ȣ");
				else if(i==2) cell.setCellValue("�ڷ�����(ǥ,�׸�,��)");
				else if(i==3) cell.setCellValue("�ڷῡ ���� ǥ�� �׸� ����(ĸ��)");
				else if(i==4) cell.setCellValue("�ڷᰡ ���� �ʹ�ȣ");
			}
		
		FileOutputStream fs = new FileOutputStream(nameToSave);
		wb.write(fs);
		fs.close();
	}
	
	public void update2(String studentId,ArrayList<String> Exels,String output) throws IOException {
		
		String name[] = output.split("\\.");
		String nameToSave = name[0] + "2." + name[1];
		
		FileInputStream input = new FileInputStream(new File(nameToSave));
		Workbook wb = WorkbookFactory.create(input);
		Sheet sheet = wb.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum();
		
		for(int i=9;i<Exels.size();i++) {
			Row row = sheet.createRow(++rowCount);
			
			for(int j=0;j<6;j++) {
				Cell cell = row.createCell(j);
				if(j==0) cell.setCellValue(studentId);
				else {
//					System.out.println(i);
//					System.out.println(Exels.get(i));
					cell.setCellValue(Exels.get(i));
					i++;
					if(i==Exels.size()) break;
				}
			}
			i--;
		}
		input.close();
        FileOutputStream outputStream = new FileOutputStream(nameToSave);
        wb.write(outputStream);
        wb.close();
        outputStream.close();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
