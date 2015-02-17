package com.kdubb.studyproblems.datastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kdubb.studyproblems.algorithm.TreeSearch;

public class TreeTest {

	@Test
	public void canCreate() {
		// Tree from http://en.wikipedia.org/wiki/Breadth-first_search
		Tree<Integer> tree = new Tree<Integer>(1);
		Tree<Integer>.TreeNode root = tree.getRoot();
		
		Tree<Integer>.TreeNode two = root.addChild(2);
		Tree<Integer>.TreeNode five = two.addChild(5);
		five.addChild(9);
		five.addChild(10);
		two.addChild(6);

		root.addChild(3);
		
		Tree<Integer>.TreeNode four = root.addChild(4);
		Tree<Integer>.TreeNode seven = four.addChild(7);
		seven.addChild(11);
		seven.addChild(12);
		four.addChild(8);
		
		assertEquals(new Integer(1), root.getValue());
		assertEquals(3, root.getChildren().size());
		assertEquals(new Integer(2), root.getChildren().get(0).getValue());
		assertEquals(new Integer(3), root.getChildren().get(1).getValue());
		assertEquals(new Integer(4), root.getChildren().get(2).getValue());
		assertEquals(2, root.getChildren().get(2).getChildren().size());
	}
	
	@Test
	public void canBreadthFirst() {
		// Tree from http://en.wikipedia.org/wiki/Breadth-first_search
		Tree<Integer> tree = new Tree<Integer>(1);
		Tree<Integer>.TreeNode root = tree.getRoot();
		
		Tree<Integer>.TreeNode two = root.addChild(2);
		Tree<Integer>.TreeNode five = two.addChild(5);
		five.addChild(9);
		five.addChild(10);
		two.addChild(6);

		root.addChild(3);
		
		Tree<Integer>.TreeNode four = root.addChild(4);
		Tree<Integer>.TreeNode seven = four.addChild(7);
		seven.addChild(11);
		seven.addChild(12);
		four.addChild(8);
		
		List<Integer> result = TreeSearch.breadthFirstSearch(tree);
		
		assertEquals(12, result.size());
		
		for(int i = 0; i < 12; i++) {
			assertEquals(new Integer(i + 1), result.get(i));
		}
	}
	
	@Test
	public void canDepthFirstSearch() {
		// Tree from http://en.wikipedia.org/wiki/Depth-first_search
		Tree<Integer> tree = new Tree<Integer>(1);
		Tree<Integer>.TreeNode root = tree.getRoot();
		
		Tree<Integer>.TreeNode two = root.addChild(2);
		Tree<Integer>.TreeNode three = two.addChild(3);
		three.addChild(4);
		three.addChild(5);
		two.addChild(6);

		root.addChild(7);
		
		Tree<Integer>.TreeNode eight = root.addChild(8);
		Tree<Integer>.TreeNode seven = eight.addChild(9);
		seven.addChild(10);
		seven.addChild(11);
		eight.addChild(12);
		
		List<Integer> result = TreeSearch.depthFirstSearch(tree);
		
		assertEquals(12, result.size());
		
		for(int i = 0; i < 12; i++) {
			assertEquals(new Integer(i + 1), result.get(i));
		}
	}
}