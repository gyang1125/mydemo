package com.ygl.pattern1;

/**
 * <p>
 * 模式1
 * </p>
 * 
 * 采用二分查找（Binary Search）的变种。二分查找是指定一个数，这里只是让你找最大值。
 *
 */
class MaxInBitonicArray {

	public static int findMax(int[] arr) {
		int start = 0, end = arr.length - 1;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] > arr[mid + 1]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		// at the end of the while loop, 'start == end'
		return arr[start];
	}

	public static void main(String[] args) {
		System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
		System.out.println(MaxInBitonicArray.findMax(new int[] { 3, 8, 3, 1 }));
		System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12 }));
		System.out.println(MaxInBitonicArray.findMax(new int[] { 10, 9, 8 }));
	}
}