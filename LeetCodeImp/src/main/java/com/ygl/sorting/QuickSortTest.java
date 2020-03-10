package com.ygl.sorting;

import java.util.Arrays;

public class QuickSortTest {

	public static void main(String[] args) {
		int[] array = { 5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8 };
		printJava7(array);
		qsort(array, 0, array.length - 1);
		printJava7(array);
//		printJava8(sorted_array);
	}

	public static void qsort(int arr[], int start, int end) {
		int pivot = arr[start];// ref number
		int i = start; // 左指针
		int j = end; // 右指针
		if (start >= end) {
			return;
		}
		while (i < j) {
			while ((i < j) && (arr[j] >= pivot)) {
				j--;
			}
			while ((i < j) && (arr[i] <= pivot)) {
				i++;
			}

			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;

		}
		//循环结束，i = j，连个指针指向同一个元素
		arr[start] = arr[i];
		arr[i] = pivot;

		qsort(arr, start, j - 1);
		qsort(arr, j + 1, end);

	}

	private static void printJava8(int[] sorted_array) {
		Arrays.stream(sorted_array).forEach(x -> System.out.print(x + ", "));
	}

	private static void printJava7(int[] sorted_array) {
		System.out.println(Arrays.toString(sorted_array));
	}

}
