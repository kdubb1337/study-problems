package com.kdubb.studyproblems.algorithm;

import com.kdubb.studyproblems.datastructure.List;
import com.kdubb.studyproblems.datastructure.Queue;
import com.kdubb.studyproblems.datastructure.Tree;

public class TreeSearch<T> {
	
	public static <T> List<T> breadthFirstSearch(Tree<T> tree) {
		System.out.println("Starting Breadth First Search");
		
		List<T> result = new List<T>();
		Queue<Tree<T>.TreeNode> queue = new Queue<Tree<T>.TreeNode>();
		queue.push(tree.getRoot());
		
		while(queue.size() != 0) {
			Tree<T>.TreeNode t = queue.pop();
			result.add(t.getValue());
			System.out.println("bfs --> " + t.getValue());
			
			for(int i = 0; i < t.getChildren().size(); i++) {
				Tree<T>.TreeNode child = t.getChildren().get(i);
				queue.push(child);
			}
		}
		
		return result;
	}
	
	public static <T> List<T> depthFirstSearch(Tree<T> tree) {
		return depthFirstSearchInner(tree.getRoot());
	}
	
	private static <T> List<T> depthFirstSearchInner(Tree<T>.TreeNode node) {
		List<T> result = new List<>();
		result.add(node.getValue());
		System.out.println("dfs --> " + node.getValue());
		
		for(int i = 0; i < node.getChildren().size(); i++) {
			List<T> childResult = depthFirstSearchInner(node.getChildren().get(i));
			result.addAll(childResult);
		}
		
		return result;
	}
}