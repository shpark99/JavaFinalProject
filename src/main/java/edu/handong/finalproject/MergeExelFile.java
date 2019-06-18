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

import edu.handong.finalproject.datamodel.ExelFile;
import edu.handong.finalproject.utils.ExcelWriter;
import edu.handong.finalproject.utils.ReadZipFileThreads;

public class MergeExelFile {
	
	private String input;
	private String output;
	private boolean help;
	private HashMap<String,ExelFile> Exels = new HashMap<String,ExelFile>();

	public void run(String[] args) throws IOException, InterruptedException {
		
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
	
	public void readZipFile(String input) throws InterruptedException {
		ReadZipFileThreads read = new ReadZipFileThreads();
		Exels = read.readZipFileWithThread(input);
	}
	
/**
 * This method is to merge all the files in one excel or csv file
 * @param sortedExels
 * @param output
 * @throws IOException
 */
	public void MergeFiles(Map<String,ExelFile> sortedExels, String output) throws IOException{
		ExcelWriter write = new ExcelWriter();
		write.write1(output);
		write.write2(output);
		for(String studentId : sortedExels.keySet()) {
			ExelFile f = sortedExels.get(studentId);
			write.update1(studentId,f.getData1(),output);
			write.update2(studentId,f.getData2(),output);
		}
	}
	
/**
 * This method is to parse options to use.
 * @param options
 * @param args
 * @return
 */
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
	
/**
 * This method is for definition stage - Add options by using OptionBuilder.
 * @return
 */
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
	
/**
 * This method is for print out help message to user
 * @param options
 */
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Java Final Project";
		String footer ="";
		formatter.printHelp("Java Finla Project", header, options, footer, true);
	}
}	
