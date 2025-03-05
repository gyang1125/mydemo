package com.ygl.sorting;

import java.util.Arrays;

/**
 * 比较两个数，把最大的数通过循环置换道数列尾部。
 * 
 * @author gyang
 *
 */
public class BubbleSortTest {

	public static void main(String[] args) {
		int[] array = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
		// 只需要修改成对应的方法名就可以了
		bubbleSort(array);

		System.out.println(Arrays.toString(array));
	}

	public static void bubbleSort(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}

		int length = array.length;

		// 外层循环控制比较轮数i
		for (int i = 0; i < length; i++) {
			// 内层循环控制每一轮比较次数，每进行一轮排序都会找出一个较大值
			// (array.length - 1)防止索引越界,因为是要比较j 和 j+1。(array.length - 1 - i)减少比较次数
			for (int j = 0; j < length - 1 - i; j++) {
				// 前面的数大于后面的数就进行交换
				if (array[j] > array[j + 1]) {
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
				}
			}
		}

	}
}
