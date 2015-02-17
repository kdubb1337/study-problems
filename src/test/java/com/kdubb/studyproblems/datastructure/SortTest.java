package com.kdubb.studyproblems.datastructure;

import java.util.Random;

import org.junit.Test;

import com.kdubb.studyproblems.algorithm.MergeSort;

public class SortTest {

	@Test
	public void canMergeSort() {
		List<Integer> toTest = new List<>();
		
		int size = 1000;
		
//		for(int i = size; i > 0; i--) {
//			toTest.add(i);
//		}
		
		Random random = new Random();
		
		for(int i = 0; i < size; i++) {
			toTest.add(random.nextInt());
		}
		
		List<Integer> sorted = MergeSort.sort(toTest);
		
		for(int i = 0; i < size; i++) {
			System.out.println((i + 1) + " :: " + sorted.get(i));
		}
	}
}