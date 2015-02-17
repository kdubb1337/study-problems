package com.kdubb.studyproblems;

import com.kdubb.studyproblems.datastructure.Stack;

public class StackMain {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		
		System.out.println("size --> " + stack.size());
		
		for(int i = 0; i < 100000; i++) {
			stack.push(i);
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.println("pop --> " + stack.pop());
		}
		
		System.out.println("size --> " + stack.size());
	}
}
