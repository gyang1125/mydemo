package com.ygl.pattern3;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * 模式3
 * </p>
 * 
 * 如果我们需要找出给定输入所有的组合（或是排列）的话，那我们要么可以用递归的回溯法，或是迭代的BFS来解决了。
 * 
 * 简单来说就是求子集， BFS（breadth-first
 * search），原理就是先拷贝原数组，然后再把新元素与原数组中的每个旧元素相加，继而得到一个新元素，以此扩展原数组。
 *
 */
class Subsets {

	public static List<List<Integer>> findSubsets(int[] nums) {
		List<List<Integer>> subsets = new ArrayList<>();
		// start by adding the empty subset
		subsets.add(new ArrayList<>());
		for (int currentNumber : nums) {
			// we will take all existing subsets and insert the current number in them to
			// create new subsets
			int n = subsets.size();
			for (int i = 0; i < n; i++) {
				// create a new subset from the existing subset and
				// insert the current element to it
				List<Integer> set = new ArrayList<>(subsets.get(i));// copy 拷贝原有数组
				set.add(currentNumber); // add new 向原有数组中加入新数字
				subsets.add(set); // 扩展原有数组
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		List<List<Integer>> result = Subsets.findSubsets(new int[] { 1, 3 });
		System.out.println("Here is the list of subsets: " + result);

//		result = Subsets.findSubsets(new int[] { 1, 5, 3 });
//		System.out.println("Here is the list of subsets: " + result);
	}
}