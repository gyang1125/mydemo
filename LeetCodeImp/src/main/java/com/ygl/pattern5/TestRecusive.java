package com.ygl.pattern5;

import java.util.Stack;

/**
 * 
 * 替换递归，就是的利用Stack不断的循环push，然后在循环pop。<br>
 * 所以在你想不通递归的时候，就想象一下stack入栈和出栈的情景把。
 *
 */
public class TestRecusive {

	public static void main(String[] args) {
		System.out.println("Recusive: " + calculate(10));
		System.out.println("Stack: " + tranformRecursiveToCirculation(10));
	}

	public static int calculate(int n) {
		if (n != 1)
			return n + calculate(n - 1);
		else
			return 1;
	}

	public static int tranformRecursiveToCirculation(int n) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = n; i >= 1; i--) {
			stack.push(i);
		}

		int sum = 0;
		while (!stack.empty()) {
			sum += stack.pop();
		}
		return sum;
	}
}
