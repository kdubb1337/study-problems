package com.kdubb.studyproblems.datastructure;

public class Stack {

	private List items = new List();
	
	public int size() {
		return items.size();
	}
	
	public void push(Object value) {
		items.add(value);
	}
	
	public Object pop() {
		return items.remove(items.size() - 1);
	}
}
