package com.kdubb.studyproblems.datastructure;

import java.util.Random;

import org.junit.Test;

import com.kdubb.studyproblems.algorithm.MergeSort;
import com.kdubb.studyproblems.algorithm.QuickSort;

public class SortTest {

	@Test
	public void canMergeSort() {
		List<Integer> toTest = new List<>();
		
		int size = 10000;
		
		Random random = new Random();
		
		for(int i = 0; i < size; i++) {
			toTest.add(random.nextInt());
		}
		
		long start = System.currentTimeMillis();
		List<Integer> sorted = MergeSort.sort(toTest);
		long end = System.currentTimeMillis();
		
		System.out.println("mergeSort length :: " + (end - start) + "ms");
		
//		for(int i = 0; i < size; i++) {
//			System.out.println((i + 1) + " :: " + sorted.get(i));
//		}
	}
	
	@Test
	public void canQuickSort() {
		int size = 10000;
		Random random = new Random();
		Integer[] toTest = new Integer[size];
		
		for(int i = 0; i < size; i++) {
			toTest[i] = random.nextInt();
		}

		long start = System.currentTimeMillis();
		Integer[] sorted = QuickSort.sort(toTest);
		long end = System.currentTimeMillis();
		
//		for(int i = 0; i < size; i++) {
//			System.out.println((i + 1) + " :: " + sorted[i]);
//		}
		
		System.out.println("quickSort length :: " + (end - start) + "ms");
		
		
//		List<Integer> toTest = new List<>();
//		
//		Random random = new Random();
//		
//		for(int i = 0; i < size; i++) {
//			toTest.add(random.nextInt());
//		}
//		
//		List<Integer> sorted = QuickSort.sort(toTest);
//		
//		for(int i = 0; i < size; i++) {
//			System.out.println((i + 1) + " :: " + sorted.get(i));
//		}
	}
}