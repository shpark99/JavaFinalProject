package edu.handong.finalproject.datamodel;

import java.util.ArrayList;

import edu.handong.finalproject.utils.StringLinkedList;

public class ExelFile {
	private String studentId;
	private ArrayList<StringLinkedList>Excel1;
	private ArrayList<StringLinkedList>Excel2;
	
	public void setData1(ArrayList<StringLinkedList> value) {
		Excel1 = value;
	}
	public void setData2(ArrayList<StringLinkedList> value) {
		Excel2 = value;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public ArrayList<StringLinkedList> getData1() {
		return Excel1;
	}
	
	public ArrayList<StringLinkedList> getData2() {
		return Excel2;
	}
}
