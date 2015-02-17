package com.kdubb.studyproblems.problems.ch1;

public class OnePoint2 {

	public static void main(String[] args) {
		String test1 = "abcdefghijklmnopqrstuvwxyz";
		String test2 = "abcdeaf";
		
		// Solution: sort first then compare with next
		// Time Compexity:  O(n) linear since we loop over each character in the string once
		// Space Compexity: O(n) linear because the size of the storage is the same as the string size
		
		System.out.println("test1: " + reverse(test1));
		System.out.println("test2: " + reverse(test2));
	}
	
	public static String reverse(String str) {
		char[] reversed = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++) {
			int reversedPos = str.length() - i - 1;
			reversed[reversedPos] = str.charAt(i);
		}
		
		return new String(reversed);
	}
}