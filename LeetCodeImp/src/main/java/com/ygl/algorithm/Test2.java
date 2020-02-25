package com.ygl.algorithm;

/**
 * 质数，大于的自然书，只能被1和本身整除；可以用(x % 2 = 0)来反推。
 *
 */
public class Test2 {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.print(getPrime(i) == -1 ? "," : getPrime(i));
		}
	}

	public static int getPrime(int x) { // rekusive will be started from break point returned
		if (x % 2 != 0)
			return x;
		return -1;

	}
}
