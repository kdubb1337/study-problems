package com.kdubb.studyproblems;

public class Main {
	
	public static void main(String[] args) {
		int bitmask = 0x000F;
        int val = 0x0030;
        // prints "2"
        System.out.println(val | bitmask);
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