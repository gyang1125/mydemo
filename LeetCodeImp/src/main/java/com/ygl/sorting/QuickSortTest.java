package com.ygl.sorting;

import java.util.Arrays;

public class QuickSortTest {

	public static void main(String[] args) {
		int[] array = { 5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8 };
		printJava7(array);
		quickSort(array, 0, array.length - 1);
//		printJava7(array);
//		printJava8(sorted_array);
	}

	private static void quickSort(int[] array, int left, int right) {
		if (array == null || left >= right || array.length <= 1) {
			return;
		}
		int mid = partition(array, left, right);
		System.out.println(Arrays.toString(array));
		quickSort(array, left, mid);
		quickSort(array, mid + 1, right);
	}

	/*
	 * a = b++; // ++写在后面，说明前面那个东西前用了，也就是b先赋值给a了，然后b再+1
	 * 
	 * a = ++b; // ++写在前面，说明++先有效，即b要+1，然后赋值给a
	 */
	private static int partition(int[] array, int left, int right) {
		int temp = array[left];
		while (right > left) {
			// 先判断基准数和右半段的数依次比较，挡在左面找到大数则跳出循环
			while (temp <= array[right] && left < right) {
				right--;
			}
			// 当基准数大于了 arr[left]，则填坑
			if (left < right) {
				array[left] = array[right];
				left++;// 准备下一次的数
			}
			// 现在是 arr[right] 需要填坑了
			while (temp >= array[left] && left < right) {
				left++;
			}
			if (left < right) {
				array[right] = array[left];
				right--;
			}
		}
		array[left] = temp;
		return left;
	}

	private static void printJava8(int[] sorted_array) {
		Arrays.stream(sorted_array).forEach(x -> System.out.print(x + ", "));
	}

	private static void printJava7(int[] sorted_array) {
		System.out.println(Arrays.toString(sorted_array));
	}

}
