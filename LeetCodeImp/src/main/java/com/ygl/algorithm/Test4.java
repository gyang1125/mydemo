package com.ygl.algorithm;

import java.util.Scanner;

/**
 * 分解质因子 90 = 2*3*3*5
 *
 */
public class Test4 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int minPrime = 2;
		while (n >= minPrime) {
			if (n == minPrime) {
				System.out.println("minPrime: " + minPrime + ", n: " + n);
				break;
			} else if (n % minPrime == 0) {
				System.out.println("minPrime: " + minPrime + ", n: " + n);
				n = n / minPrime;
			} else {
				minPrime++;
			}
		}
	}
}
