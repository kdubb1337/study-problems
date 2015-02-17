package com.kdubb.studyproblems.datastructure;

public class Tree<T> {

	private TreeNode root;
	
	public Tree(T rootValue) {
		root = new TreeNode(rootValue);
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
	public class TreeNode {
		
		private T value;
		private List<TreeNode> children;
		
		public TreeNode(T value) {
			this.value = value;
			this.children = new List<TreeNode>();
		}
		
		public T getValue() {
			return value;
		}
		
		public List<TreeNode> getChildren() {
			return children;
		}
		
		public TreeNode addChild(T childValue) {
			TreeNode child = new TreeNode(childValue);
			children.add(child);
			return child;
		}
		
		public T removeChild(int position) {
			TreeNode node = children.remove(position);
			
			if(node ==  null) {
				return null;
			}
			
			return node.getValue();
		}
	}
}
