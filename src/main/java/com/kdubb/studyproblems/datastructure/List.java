package com.kdubb.studyproblems.datastructure;

public class List<T> {

	private ListItem<T> head;
	private ListItem<T> tail;
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void add(T obj) {
		ListItem<T> item = new ListItem<T>(obj);
		
		if(head == null || tail == null) {
			head = item;
			tail = item;
		}
		else {
			tail.next = item;
			tail = item;
		}
	}
	
	public void addAll(List<T> obj) {
		ListItem<T> item = obj.head;
		
		while(item != null) {
			this.add(item.value);
			item = item.next;
		}
	}
	
	public T remove(int position) {
		if(head == null || position < 0) {
			return null;
		}
		
		int curPosition = 0;
		ListItem<T> item = head;
		
		while(curPosition < position - 1) {
			curPosition++;
			item = item.next;
			
			if(item == null) {
				return null;
			}
		}
		
		if(position == 0) {
			head = item.next;
			
			if(head == null) {
				tail = null;
			}
			
			return item.value;
		}
		else if(item.next != null) {
			ListItem<T> removed = item.next;
			ListItem<T> temp = removed.next;
			
			if(temp == null) {
				tail = item;
				item.next = null;
			}
			else {
				item.next = temp;
			}
			
			return removed.value;
		}
		
		return null;
	}
	
	public T get(int position) {
		if(head == null) {
			return null;
		}
		
		int curPosition = 0;
		ListItem<T> item = head;
		
		while(curPosition < position) {
			curPosition++;
			item = item.next;
			
			if(item == null) {
				return null;
			}
		}
		
		return item.value;
	}
	
	public int size() {
		if(head == null) {
			return 0;
		}
		
		int size = 1;
		ListItem<T> item = head;
		
		while(item.next != null) {
			size++;
			item = item.next;
		}
		
		return size;
	}
	
	private class ListItem<A extends T> {
		private T value;
		private ListItem<A> next;
		
		public ListItem(A value) {
			this.value = value;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		if(!isEmpty())
			sb.append(get(0));
		
		for(int i = 1; i < size(); i++) {
			sb.append(", ").append(get(i));
		}
		
		sb.append("]");
		return sb.toString();
	}
}