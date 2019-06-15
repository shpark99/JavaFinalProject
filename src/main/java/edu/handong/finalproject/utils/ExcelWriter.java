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

import edu.handong.finalproject.datamodel.ExelFile;

public class ExcelWriter {
	
	public void write1() throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Data1");
		
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
		
		FileOutputStream fs = new FileOutputStream("Data1");
		wb.write(fs);
		fs.close();
	}
	
	public void update1(String studentId,ArrayList<String> Exels) throws IOException {
	
			FileInputStream input = new FileInputStream(new File("Data1"));
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
					}
				}
				i--;
			}
			input.close();
            FileOutputStream outputStream = new FileOutputStream("data1.xls");
            wb.write(outputStream);
            wb.close();
            outputStream.close();
		}
	
	public void write2() throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Data2");
		
			Row row = sheet.createRow(0);
			
			for(int i=0;i<8;i++) {
				Cell cell = row.createCell(i);
				
				if(i==0) cell.setCellValue("����(�ݵ�� ��๮ ��Ŀ� �Է��� ����� ���ƾ� ��.)");
				else if(i==1) cell.setCellValue("ǥ/�׸� �Ϸù�ȣ");
				else if(i==2) cell.setCellValue("�ڷ�����(ǥ,�׸�,��)");
				else if(i==3) cell.setCellValue("�ڷῡ ���� ǥ�� �׸� ����(ĸ��)");
				else if(i==4) cell.setCellValue("�ڷᰡ ���� �ʹ�ȣ");
			}
		
		FileOutputStream fs = new FileOutputStream("Data2");
		wb.write(fs);
		fs.close();
	}
	
	public void update2(String studentId,ArrayList<String> Exels) throws IOException {
		
		FileInputStream input = new FileInputStream(new File("Data2"));
		Workbook wb = WorkbookFactory.create(input);
		Sheet sheet = wb.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum();
		
		for(int i=9;i<Exels.size();i++) {

			Row row = sheet.createRow(++rowCount);
			
			for(int j=0;j<6;j++) {
				Cell cell = row.createCell(j);
				if(j==0) cell.setCellValue(studentId);
				else {
					cell.setCellValue(Exels.get(i));
					i++;
				}
				System.out.println(Exels.get(i));
			}
			i--;
		}
		input.close();
        FileOutputStream outputStream = new FileOutputStream("data2.xls");
        wb.write(outputStream);
        wb.close();
        outputStream.close();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
