package com.ygl.algorithm;

/**
 * 斐波那契数列 F(n) = F(n-1) + F(n-2), n>=2 1, 1, 2, 3, 5, 8
 *
 */
public class Fibonacci {
	public static void main(String[] args) {
		for (int i = 0; i <= 5; i++) {
			fib(i);
			System.out.println("--");
		}

	}

	private static void getF(int m) {
		int temp = 0, f1 = 1, f2 = 1;
		for (int i = 1; i <= m; i++) {
			System.out.println(f1);
			temp = f2;
			f2 += f1;
			f1 = temp;
		}
	}

	public static int fib(int n) {
		System.out.println("input index: " + n);
		if (n <= 1) {
			System.out.println("return leaf: " + n);
			return n;

		} else {
			int sum = fib(n - 1) + fib(n - 2);
			System.out.println("return sum: " + sum);
			return sum;
		}
	}

}
