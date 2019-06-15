package edu.handong.finalproject.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class Utils {
	
	public void readZipFile(String input) {
		File dirFile = new File(input);
		File [] fileList = dirFile.listFiles();
		for(File tempFile: fileList) {
			String tempPath = tempFile.getAbsolutePath();
			System.out.println(tempPath);
		
			ZipFile zipFile;
			try {
				zipFile = new ZipFile(tempPath);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				
				while(entries.hasMoreElements()) {
					ZipArchiveEntry entry = entries.nextElement();
					InputStream stream = zipFile.getInputStream(entry);
					
					ExcelReader reader = new ExcelReader();
					
					for(String value:reader.getData(stream)) { 
			        	System.out.println(value); 
					 } 
				} 
			} catch (IOException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
			} 
		}
	} 
}
