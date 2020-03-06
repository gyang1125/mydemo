package com.ygl.pattern1;

/**
 * <p>
 * 模式1
 * </p>
 * 
 * 前提是这个数组必须是一个有序的数组
 *
 */
class PairWithTargetSum {

	public static int[] search(int[] arr, int targetSum) {
		int left = 0, right = arr.length - 1;
		while (left < right) {
			// comparing the sum of two numbers to the 'targetSum' can cause integer
			// overflow
			// so, we will try to find a target difference instead
			// 直接计算两个数的和，会导致整型溢出，所以我们比较两个数的差
			int targetDiff = targetSum - arr[left];
			if (targetDiff == arr[right])
				return new int[] { left, right }; // found the pair

			if (targetDiff > arr[right])
				left++; // we need a pair with a bigger sum
			else
				right--; // we need a pair with a smaller sum
		}
		return new int[] { -1, -1 };
	}

	public static void main(String[] args) {
		int[] result = PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
		System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
		result = PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
		System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
	}
}