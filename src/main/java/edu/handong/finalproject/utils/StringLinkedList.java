package edu.handong.finalproject.utils;

public class StringLinkedList {
	
	private ListNode head;
	private ListNode current;
	private ListNode previous;
	
	public StringLinkedList() {
		head = null;
		current = null;
		previous = null;
	}

	public void showList() {
		ListNode position = head;
		while(position != null) {
			System.out.println(position.data);
			position = position.link;
			
		}
	}
	
	public int length() {
		int count = 0;
		ListNode position = head;
		while(position != null) {
			count++;
			position = position.link;
		}
		return count;
	}
	
	public void resetIteration() {
		current = head;
		previous = null;
	}
	
	public boolean moreToIterate() {
		return current != null;
	}
	
	public void goToNext() {
		if(current != null) {
			previous = current;
			current = current.link;
		}else if(head!=null) {
			System.out.println("Iterated too many times or uninitialized iteration.");
			System.exit(0);
		}else {
			System.out.println("Iterating with an empty list.");
			System.exit(0);
		}
	}
	
	public String getDataAtCurrent() {
		String result = null;
		if(current != null) {
			result = current.data;
		}else {
			System.out.println("Getting data when current is not at any node");
			System.exit(0);
		}
		return result;
	}
	
	public void setDataAtCurrent(String newData) {
		if(current != null) {
			current.data = newData;
		}else {
			System.out.println("Setting data when current is not any node");
			System.exit(0);
		}
	}
	
	public void insertNodeAfterCurrent(String newData) {
		ListNode newNode = new ListNode();
		newNode.data = newData;
		if(head==null) {
			head = new ListNode(newData, head);
			current = head;
		}else if(current != null) {
			newNode.link = current.link;
			current.link = newNode;
			current = newNode;
		}else if(head != null) {
			System.out.println("Inserting when iterator is past all nodes or is not initialzed");
			System.exit(0);
		}else {
			System.out.println("Using insertNodeAfterCurrent with empty list.");
			System.exit(0);
		}
	}
		
	private class ListNode{
		private String data;
		private ListNode link;
		
		public ListNode() {
			link = null;
			data = null;
		}
		
		public ListNode(String newData, ListNode linkedNode) {
			data = newData;
			link = linkedNode;
		}
	}
}
