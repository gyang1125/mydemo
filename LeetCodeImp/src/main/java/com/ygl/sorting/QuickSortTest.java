package com.ygl.sorting;

import java.util.Arrays;

public class QuickSortTest {

	public static void main(String[] args) {
		int[] array = { 5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8 };
		printJava7(array);
		int[] sorted_array = qsort(array, 0, array.length - 1);
		printJava7(sorted_array);
//		printJava8(sorted_array);
	}

	public static int[] qsort(int arr[], int start, int end) {
		int pivot = arr[(int) (start + Math.random() * (end - start + 1))];// ref number
		int i = start; // 左指针
		int j = end; // 右指针
		while (i < j) {
			while ((i < j) && (arr[j] > pivot)) {// 右边第一个比pivot小的值
				j--;
			}
			while ((i < j) && (arr[i] < pivot)) {// 左边第一个比pivot大的值
				i++;
			}
			if ((arr[i] == arr[j]) && (i < j)) {
				i++;
			} else {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		if (i - 1 > start) // 左分区，左指针回撤
			arr = qsort(arr, start, i - 1);
		if (j + 1 < end) // 右分区，右指针回撤
			arr = qsort(arr, j + 1, end);
		return (arr);
	}

	private static void printJava8(int[] sorted_array) {
		Arrays.stream(sorted_array).forEach(x -> System.out.print(x + ", "));
	}

	private static void printJava7(int[] sorted_array) {
		System.out.println(Arrays.toString(sorted_array));
	}

}
