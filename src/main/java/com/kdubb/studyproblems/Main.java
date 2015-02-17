package com.kdubb.studyproblems;

import com.kdubb.studyproblems.datastructure.List;
import com.kdubb.studyproblems.datastructure.Queue;
import com.kdubb.studyproblems.datastructure.Stack;

public class Main {
	
	public static void main(String[] args) {
		Stack queue = new Stack();
		
		System.out.println("size --> " + queue.size());
		
		queue.push(5);
		queue.push(1);
		queue.push(3);

		System.out.println("0 --> " + queue.pop());
		System.out.println("1 --> " + queue.pop());
		System.out.println("2 --> " + queue.pop());
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