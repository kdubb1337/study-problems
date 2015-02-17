package com.kdubb.studyproblems.datastructure;

public class List {

	private ListItem head;
	private ListItem tail;
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void add(Object obj) {
		ListItem item = new ListItem(obj);
		
		if(head == null || tail == null) {
			head = item;
			tail = item;
		}
		else {
			tail.next = item;
			tail = item;
		}
	}
	
	public Object remove(int position) {
		if(head == null || position < 0) {
			return null;
		}
		
		int curPosition = 0;
		ListItem item = head;
		
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
			ListItem removed = item.next;
			ListItem temp = removed.next;
			
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
	
	public Object get(int position) {
		if(head == null) {
			return null;
		}
		
		int curPosition = 0;
		ListItem item = head;
		
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
		ListItem item = head;
		
		while(item.next != null) {
			size++;
			item = item.next;
		}
		
		return size;
	}
	
	private class ListItem {
		private Object value;
		private ListItem next;
		
		public ListItem(Object value) {
			this.value = value;
		}
	}
}