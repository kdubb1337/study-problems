package com.kdubb.studyproblems.algorithm;

import com.kdubb.studyproblems.datastructure.List;

public class MergeSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> toSort) {
		if(toSort.size() <= 1) {
			return toSort;
		}
		
		List<T> left = new List<>();
		List<T> right = new List<>();
		int middle = toSort.size() / 2;
		
		for(int i = 0; i < toSort.size(); i++) {
			if(i < middle) {
				left.add(toSort.get(i));
			}
			else {
				right.add(toSort.get(i));
			}
		}
		
		left = sort(left);
		right = sort(right);
		
		return innerSort(left, right);
	}
	
	private static <T extends Comparable<T>> List<T> innerSort(List<T> left, List<T> right) {
		List<T> result = new List<>();
		
		while(!left.isEmpty() && !right.isEmpty()) {
			T firstLeft = left.get(0);
			T firstRight = right.get(0);
			
			if(firstLeft.compareTo(firstRight) < 0) {
				result.add(left.remove(0));
			}
			else {
				result.add(right.remove(0));
			}
		}
		
		while(!left.isEmpty()) {
			result.add(left.remove(0));
		}
		
		while(!right.isEmpty()) {
			result.add(right.remove(0));
		}
		
		return result;
	}
}