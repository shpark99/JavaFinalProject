package edu.handong.finalproject.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.finalproject.datamodel.ExelFile;

public class ReadZipFileThreads implements Runnable{
	
	private String tempPath;
	private String studentId;
	private ExelFile file;
	
	public ReadZipFileThreads() {

	}
	
	public ReadZipFileThreads(String studentId,String tempPath) {
		this.studentId = studentId;
		this.tempPath = tempPath;
	}

	public HashMap<String,ExelFile> readZipFileWithThread(String input) throws InterruptedException{
		
		ArrayList<ReadZipFileThreads> readRunners = new ArrayList<ReadZipFileThreads>();
		HashMap<String,ExelFile> Exels = new HashMap<String,ExelFile>();
		
		File dirFile = new File(input);
		File [] fileList = dirFile.listFiles();
		String studentId;
		
		for(File tempFile: fileList) {
			String tempPath = tempFile.getAbsolutePath();
			studentId = tempFile.getName().split("\\.")[0];
			readRunners.add(new ReadZipFileThreads(studentId,tempPath));
			}
			
		ArrayList<Thread> threadsForEachFiles = new ArrayList<Thread>();
	
		for(ReadZipFileThreads runner:readRunners) {
			Thread thread = new Thread(runner);
			thread.start();
			threadsForEachFiles.add(thread);
		}
			
		for(Thread t: threadsForEachFiles) {
			t.join();
		}
		for(ReadZipFileThreads runner:readRunners) {
			Exels.put(runner.studentId, runner.file);
		}
		
		return Exels;
	}
	
	public void run() {
		ZipFile zipFile;
		file = new ExelFile();
		int num = 1;
		try { 
			zipFile = new ZipFile(tempPath);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			
			//Get Student Id from file name
			file.setStudentId(studentId);
			
			while(entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				InputStream stream = zipFile.getInputStream(entry);
				
				ExcelReader reader = new ExcelReader();
				if(num==1) {
					file.setData1(reader.getData(stream));
					num++;
				}else if(num==2){
					file.setData2(reader.getData(stream));
				}
				stream.close();
			}
			zipFile.close();
		}catch(IOException e) { 
			e.printStackTrace(); 
		}
	}
}
