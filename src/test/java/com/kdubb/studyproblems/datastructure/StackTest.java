package com.kdubb.studyproblems.datastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackTest {

	@Test
	public void canFindSize() {
		Stack<Integer> stack = new Stack<Integer>();
		
		assertEquals(0, stack.size());
		stack.push(null);
		assertEquals(1, stack.size());
		stack.push(1);
		stack.push(null);
		assertEquals(3, stack.size());
		stack.pop();
		assertEquals(2, stack.size());
	}
	
	@Test
	public void canPush() {
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < 100; i++) {
			stack.push(i);
		}
		
		assertEquals(100, stack.size());
		assertEquals(new Integer(99), stack.pop());
		assertEquals(new Integer(98), stack.pop());
		assertEquals(new Integer(97), stack.pop());
		assertEquals(97, stack.size());
	}
}
