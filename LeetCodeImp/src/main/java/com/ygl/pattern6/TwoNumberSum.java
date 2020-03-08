package com.ygl.pattern6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * * 模式6： <br>
 * 如果某个问题存在O(n2)的时间和O(1)空间的暴力解，那么它肯定存在以下两种解决方案：<br>
 * 1）用Map或是Set来实现O(n)时间和O(n)空间的复杂度方案，<br>
 * 2）用排序来实现O(nlogn)的时间和O(1)空间的解。
 * 
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 示例: 给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1] HashMap利用hashcode列表查找时间复杂度为O(1)，所以内层比再用一次for要好。
 * 
 */
public class TwoNumberSum {
	public static void main(String[] args) {
		int[] test = new int[] { 2, 7, 11, 15 };
		int target = 9;

		System.out.println(Arrays.toString(twoSumBoli(test, target)));
		System.out.println(Arrays.toString(twoSumWithTwoMaps(test, target)));
		System.out.println(Arrays.toString(twoSumWithOneMap(test, target)));
	}

	public static int[] twoSumBoli(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] == target - nums[i]) {
					return new int[] { i, j };
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	public static int[] twoSumWithTwoMaps(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		// 首先把int[]用一个循环全部转换为map，数值作为key，下标作为value
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		// 再次循环便利这个int[],不过利用map的hashcode来做对比。
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement) && map.get(complement) != i) {
				return new int[] { i, map.get(complement) };
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	public static int[] twoSumWithOneMap(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement)) {
				return new int[] { map.get(complement), i };
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}

}
