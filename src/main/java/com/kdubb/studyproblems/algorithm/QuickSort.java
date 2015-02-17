package com.kdubb.studyproblems.algorithm;

import com.kdubb.studyproblems.datastructure.List;

public class QuickSort {
	
	public static String sort(String str) {
		return new String(innerSort(str.getBytes(), 0, str.length() - 1));
	}
	
	public static byte[] sort(byte[] toSort) {
		return innerSort(toSort, 0, toSort.length - 1);
	}
	
	private static  byte[] innerSort(byte[] A, int lo, int hi) {
		if(lo < hi) {
			int p = partition(A, lo, hi);
			innerSort(A, lo, p - 1);
			innerSort(A, p + 1, hi);
		}
		
		return A;
	}
	
	private static int partition(byte[] A, int lo, int hi) {
		int pivotIndex = (hi - lo) / 2 + lo;
		byte pivotValue = A[pivotIndex];
		
		// put the chosen pivot at A[hi]
		byte temp = A[hi];
		A[hi] = pivotValue;
		A[pivotIndex] = temp;
		
		int storeIndex = lo;
		
		for(int i = lo; i < hi; i++) {
			if(A[i] < pivotValue) { 
				// swap A[i] and A[storeIndex]
				temp = A[i];
				A[i] = A[storeIndex];
				A[storeIndex] = temp;
				
				storeIndex++;
			}
		}
		
		// swap A[storeIndex] and A[hi], move pivot to its final place
		temp = A[storeIndex];
		A[storeIndex] = A[hi];
		A[hi] = temp;
		return storeIndex;
	}
	
	
	
	public static <T extends Comparable<T>> T[] sort(T[] toSort) {
		return innerSort(toSort, 0, toSort.length - 1);
	}
	
	private static <T extends Comparable<T>> T[] innerSort(T[] A, int lo, int hi) {
		if(lo < hi) {
			int p = partition(A, lo, hi);
			innerSort(A, lo, p - 1);
			innerSort(A, p + 1, hi);
		}
		
		return A;
	}
	
	private static <T extends Comparable<T>> int partition(T[] A, int lo, int hi) {
		int pivotIndex = (hi - lo) / 2 + lo;
		T pivotValue = A[pivotIndex];
		
		// put the chosen pivot at A[hi]
		T temp = A[hi];
		A[hi] = pivotValue;
		A[pivotIndex] = temp;
		
		int storeIndex = lo;
		
		for(int i = lo; i < hi; i++) {
			if(A[i].compareTo(pivotValue) < 0) { 
				// swap A[i] and A[storeIndex]
				temp = A[i];
				A[i] = A[storeIndex];
				A[storeIndex] = temp;
				
				storeIndex++;
			}
		}
		
		// swap A[storeIndex] and A[hi], move pivot to its final place
		temp = A[storeIndex];
		A[storeIndex] = A[hi];
		A[hi] = temp;
		return storeIndex;
	}
	
	// Seems to have O(n) instead of O(log n)
	public static <T extends Comparable<T>> List<T> sort(List<T> toSort) {
		if(toSort.size() <= 1) {
			return toSort;
		}

		System.out.println("toSort=" + toSort.size());
		
		List<T> left = new List<>();
		List<T> right = new List<>();
		int middle = toSort.size() / 2;
		T pivotValue = toSort.get(middle);
		
		for(int i = 0; i < toSort.size(); i++) {
			if(i == middle)
				continue;
			
			T curValue = toSort.get(i);
			
			if(curValue.compareTo(pivotValue) < 0) {
				left.add(curValue);
			}
			else {
				right.add(curValue);
			}
		}
		
		if(left.size() == 0) {
			right = sort(right);
			List<T> result = new List<>();
			result.add(pivotValue);
			result.addAll(right);
			return result;
		}
		else if(right.size() == 0) {
			left = sort(left);
			left.add(pivotValue);
			return left;
		}

		left = sort(left);
		right = sort(right);
		
		left.add(pivotValue);
		left.addAll(right);
		return left;
	}
}