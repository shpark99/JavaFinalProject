package edu.handong.finalproject.datamodel;

import java.util.ArrayList;

import edu.handong.finalproject.utils.StringLinkedList;

public class ExelFile {
	private String studentID;
	private ArrayList<StringLinkedList>Excel1;
	private ArrayList<StringLinkedList>Excel2;
	
	public void setData1(ArrayList<StringLinkedList> value) {
		Excel1 = value;
	}
	public void setData2(ArrayList<StringLinkedList> value) {
		Excel2 = value;
	}
	public void getStudentId(String studentId) {
		this.studentID = studentId;
	}
	
	public ArrayList<StringLinkedList> getData1() {
		return Excel1;
	}
	
	public ArrayList<StringLinkedList> getData2() {
		return Excel2;
	}
}
