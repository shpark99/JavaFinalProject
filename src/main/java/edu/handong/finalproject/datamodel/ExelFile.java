package edu.handong.finalproject.datamodel;

import java.util.ArrayList;

public class ExelFile {
	private String studentID;
	private ArrayList<String>Exel1;
	private ArrayList<String>Exel2;
	
	public void setData1(ArrayList<String> value) {
		Exel1 = value;
	}
	public void setData2(ArrayList<String> value) {
		Exel2 = value;
	}
	public void getStudentId(String studentId) {
		this.studentID = studentId;
	}
	
	public ArrayList<String> getData1() {
		return Exel1;
	}
	
	public ArrayList<String> getData2() {
		return Exel2;
	}
}
