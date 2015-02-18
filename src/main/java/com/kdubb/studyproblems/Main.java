package com.kdubb.studyproblems;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.assertEquals("a", "a");
		main.assertEquals(1, 1);
		main.assertEquals(true, ("a".equals("a")));
	}
	
	public boolean assertEquals(Object expected, Object actual) {
		// Get rid of an edge case
		if(expected == null && actual == null) {
			return true;
		}
		
		// If one is still null, they are not equal
		if(expected == null || actual == null) {
			throw new RuntimeException("Expected [" + expected + "] but found [" + actual + "]");
		}
	
		// If their classes don't match they are not equal
		if(!expected.getClass().equals(actual.getClass())) {
			throw new ClassCastException("Expected object of class [" + expected.getClass() + "] but found [" + actual.getClass() + "]");
		}
		
		// If they aren't equal throw exception
		if(!expected.equals(actual)) {
			throw new RuntimeException("Expected [" + expected + "] but found [" + actual + "]");
		}
		
		return true;
	}
	
	public static long fibonacciIterative(long n) {
		if(n <= 1) {
			return n;
		}
		
		long a = 0;
		long b = 1;
		
		for(int i = 1; i < n; i++) {
			long temp = a;
			a = b;
			b += temp;
		}
		
		return b;
	}
	
	public static long fibonacciRecursive(long n) {
		if(n <= 1) {
			return n;
		}
		
		return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
	}
}