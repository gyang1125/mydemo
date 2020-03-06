package com.ygl.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 
 * HashMap利用hashcode列表查找时间复杂度为O(1)，所以内层比再用一次for要好。
 * 
 */
public class TwoNumberSum {
	public static void main(String[] args) {

	}

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] res = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int dif = target - nums[i];
			if (map.get(dif) != null) {
				res[0] = map.get(dif);
				res[1] = i;
				return res;
			}
			map.put(nums[i], i);
		}
		return res;
	}
}
