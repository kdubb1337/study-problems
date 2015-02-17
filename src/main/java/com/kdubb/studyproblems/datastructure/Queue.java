package com.kdubb.studyproblems.datastructure;

public class Queue<T> {

	private List<T> items = new List<T>();
	
	public int size() {
		return items.size();
	}
	
	public void push(T value) {
		items.add(value);
	}
	
	public T pop() {
		return items.remove(0);
	}
}