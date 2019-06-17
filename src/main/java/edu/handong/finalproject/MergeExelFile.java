package edu.handong.finalproject;

import java.util.zip.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import edu.handong.analysis.datamodel.Student;
import edu.handong.finalproject.datamodel.ExelFile;
import edu.handong.finalproject.utils.ExcelReader;
import edu.handong.finalproject.utils.ExcelWriter;
import edu.handong.finalproject.utils.Utils;

public class MergeExelFile {
	
	private String input;
	private String output;
	private boolean help;
	private HashMap<String,ExelFile> Exels = new HashMap<String,ExelFile>();

	public void run(String[] args) throws IOException {
		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			readZipFile(input);
			Map<String,ExelFile> sortedExels = new TreeMap<String,ExelFile>(Exels); 
			MergeFiles(sortedExels,output);
		}	
	}
	
	
	public void readZipFile(String input) throws IOException {
		File dirFile = new File(input);
		File [] fileList = dirFile.listFiles();
		String studentId;
		
		for(File tempFile: fileList) {
			String tempPath = tempFile.getAbsolutePath();
			System.out.println(tempPath);
			int num = 1;
			ExelFile file = new ExelFile();
			
			ZipFile zipFile;
			try { 
				zipFile = new ZipFile(tempPath);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				
				//Get Student Id from file name
				studentId = tempFile.getName().split("\\.")[0];
				file.getStudentId(studentId);
				
				while(entries.hasMoreElements()) {
					ZipArchiveEntry entry = entries.nextElement();
					InputStream stream = zipFile.getInputStream(entry);
					
					ExcelReader reader = new ExcelReader();
					if(num==1) {
						file.setData1(reader.getData(stream));
						num++;
					}else if(num==2){
						file.setData2(reader.getData(stream));
						Exels.put(studentId,file);
					}
					stream.close();
				}
			} catch (IOException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
			} 
		}
	} 

	void MergeFiles(Map<String,ExelFile> sortedExels, String output) throws IOException{
		ExcelWriter write = new ExcelWriter();
		write.write1(output);
		write.write2(output);
		for(String studentId : sortedExels.keySet()) {
			ExelFile f = sortedExels.get(studentId);
			write.update1(studentId,f.getData1(),output);
			write.update2(studentId,f.getData2(),output);
		}
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg() 
				.argName("Output path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Java Final Project";
		String footer ="";
		formatter.printHelp("Java Finla Project", header, options, footer, true);
	}
}	
