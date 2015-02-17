package com.kdubb.studyproblems.problems.ch1;

import com.kdubb.studyproblems.algorithm.QuickSort;

public class OnePoint1 {

	public static void main(String[] args) {
		// Implement an algorithm to determine if a string has all unique characters.
		// What if you can not use additional data structures?
		
		String test1 = "abcdefghijklmnopqrstuvwxyz";
		String test2 = "abcdeaf";
		
		// Solution: sort first then compare with next
		// Time Compexity: O(n log n) loglinear due to sort (but beats O(n^2) quadratic from brute force)
		
		System.out.println("test1: " + isAllUnique(QuickSort.sort(test1)));
		System.out.println("test2: " + isAllUnique(QuickSort.sort(test2)));
	}
	
	public static boolean isAllUnique(String str) {
		char prev = str.charAt(0);
		
		for(int i = 1; i < str.length(); i++) {
			if(prev == str.charAt(i)) {
				return false;
			}
			
			prev = str.charAt(i);
		}
		
		return true;
	}
}