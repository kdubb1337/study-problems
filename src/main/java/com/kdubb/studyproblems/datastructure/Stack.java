package com.kdubb.studyproblems.datastructure;

public class Stack<T> {

	private List<T> items = new List<T>();
	
	public int size() {
		return items.size();
	}
	
	public void push(T value) {
		items.add(value);
	}
	
	public T pop() {
		return items.remove(items.size() - 1);
	}
}
