package com.ygl.es.job;

/**
 * 斐波那契数列 F(n) = F(n-1) + F(n-2), n>=2
 *
 */
public class Test1 {
	public static void main(String[] args) {
		for (int i = 1; i <= 3; i++) {
			System.out.println(f(i));
		}
	}

	public static int f(int x) { // rekusive will be started from break point returned
		if (x == 1 || x == 2)
			return 1;
		else
			return f(x - 1) + f(x - 2);
	}
}
