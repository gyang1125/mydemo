package com.ygl.search;

/**
 * 
 * 计算中值
 *
 * 算法一： mid = (low + high) / 2 <br>
 * 算法二： mid = low + (high – low)/2
 * 
 * 乍看起来，算法一简洁，算法二提取之后，跟算法一没有什么区别。但是实际上，区别是存在的。算法一的做法，在极端情况下，(low +
 * high)存在着溢出的风险，进而得到错误的mid结果，导致程序错误。而算法二能够保证计算出来的mid，一定大于low，小于high，不存在溢出的问题。
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] data = {1, 4, 6, 18, 34, 89, 77};
		int target = 34;
		System.out.println(binarysearch1(data, 0, data.length-1, 34));
	}

	static int binarysearch1(int array[], int low, int high, int target) {
		if (low > high)
			return -1;
		int mid = low + (high - low) / 2;
		if (array[mid] > target)
			return binarysearch1(array, low, mid - 1, target);
		if (array[mid] < target)
			return binarysearch1(array, mid + 1, high, target);
		return mid;
	}

	int bsearchWithoutRecursion(int a[], int key) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (a[mid] > key)
				high = mid - 1;
			else if (a[mid] < key)
				low = mid + 1;
			else
				return mid;
		}
		return -1;

	}
}
