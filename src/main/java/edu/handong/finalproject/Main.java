package edu.handong.finalproject;

import java.io.IOException;

import edu.handong.finalproject.MergeExelFile;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		MergeExelFile analyzer = new MergeExelFile();
		analyzer.run(args);
	}
}