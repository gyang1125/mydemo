package com.ygl.algorithm;

import java.util.Scanner;

/**
 * 
 * 最大公约数Greatest Common Divisor(GCD)：几个数，都能共同整除一个最大的数，这个数就是他们的最大公约数。 <br>
 * 最小公倍数Least Common Multiple(LCM)：几个数，都能被同一个数整出，这就是他们的最小公倍数。 几个数的乘积除以它们的最大公约数。
 * 
 */
public class GreatestCommonDivisor {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int b = input.nextInt();

		System.out.println(getGCD(a, b));
		System.out.println(getLCM(a, b, getGCD(a, b)));
	}

	private static int getLCM(int a, int b, int gcd) {
		return a * b / gcd;
	}

	private static int getGCD(int a, int b) {
		if (a < b) { // 交换，使第一个数比第二个数大
			int temp = b;
			b = a;
			a = temp;
		}
		while (b != 0) { // b是每次循环下来的余数
			if (a == b) {
				return a;
			} else {
				int temp = b;
				b = a % b;
				a = temp;
			}
		}
		return a;
	}
}
