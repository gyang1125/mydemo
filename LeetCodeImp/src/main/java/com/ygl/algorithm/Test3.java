package com.ygl.algorithm;

/**
 * 水仙花 number == x * x * x + y * y * y + z * z * z
 *
 */
public class Test3 {
	public static void main(String[] args) {
		for (int i = 101; i <= 999; i++) {
			if (getNarcissisticNumber(i))
				System.out.println(i);
		}
	}

	public static boolean getNarcissisticNumber(int number) {
		int x = 0, y = 0, z = 0;
		x = number / 100; //得到百位数
		y = (number % 100) / 10; // 得到十位数
		z = number % 10; // 得到各位数
		if (number == x * x * x + y * y * y + z * z * z)
			return true;
		return false;
	}
}
