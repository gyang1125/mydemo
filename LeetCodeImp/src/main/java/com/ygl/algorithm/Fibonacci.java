package com.ygl.algorithm;

/**
 * 斐波那契数列 F(n) = F(n-1) + F(n-2), n>=2
 * 1, 1, 2, 3, 5, 8
 *
 */
public class Fibonacci {
	public static void main(String[] args) {
		getF(10);

	}

	private static void getF(int m) {
		int temp = 0, f1=1, f2=1;
		for (int i = 1; i <= m; i++) {
			System.out.println(f1);
			temp = f2;
			f2 += f1;
			f1 = temp;
		}
	}

}
