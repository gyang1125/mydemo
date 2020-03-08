package com.ygl.pattern7;

/**
 * 模式7：<br>
 * 
 * 如果一个题目需要优化（无论是最大化还是最小化），我们就需要借助于动态规划来解了。
 *
 *
 * 求取数组中最大连续子序列和，例如给定数组为A={-5, 1， 3， -2， 4}， 则最大连续子序列和为6，即1+3+（-2）+ 4 = 6。
 */
public class DynamicProgrammingTest {

	public static void main(String[] args) {
		int[] data = { -5, 1, 3, -2, 4 };
//		System.out.println(maxsequence(data, data.length));
//		System.out.println(maxsequence2(data, 0, data.length - 1));
		System.out.println(maxsequence3(data));
	}

	/**
	 * 动态规划<br>
	 * 最大连续子序列和只可能是以位置0～n-1中某个位置结尾。<br>
	 * 当遍历到第i个元素时，判断在它前面的连续子序列和是否大于0，如果大于0，<br>
	 * i和前面的连续子序列和相加；否则，则以第i个元素重新记为子下一个可能的序列的起点。 <br>
	 * sum[i]=max(sum[i-1]+a[i],a[i])
	 * 
	 * @param arr
	 * @return
	 */
	public static int maxsequence3(int arr[]) {
		int sum = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; ++i) {
			System.out.println(arr[i]);
			sum = sum > 0 ? sum + arr[i] : arr[i];
			if (sum > max) {
				max = sum;
			}
			System.out.println("sum: " + sum);
		}
		return max;
	}

	/**
	 * 暴力求解
	 * 
	 * @param arr
	 * @param len
	 * @return
	 */
	public static int maxsequence(int arr[], int len) {
		int max = arr[0]; // 初始化最大值为第一个元素
		for (int i = 0; i < len; i++) {
			int sum = 0; // sum必须清零
			for (int j = i; j < len; j++) { // 从位置i开始计算从i开始的最大连续子序列和的大小，如果大于max，则更新max。
				sum += arr[j];
				if (sum > max)
					max = sum;
			}
		}
		return max;
	}

	/**
	 * 该问题还可以通过分治法来求解<br>
	 * 最大连续子序列和要么出现在数组左半部分，要么出现在数组右半部分，要么横跨左右两半部分。<br>
	 * 因此求出这三种情况下的最大值就可以得到最大连续子序列和
	 * 
	 * @param arr
	 * @param len
	 * @return
	 */
	public static int maxsequence2(int a[], int l, int u) {
		if (l > u)
			return 0;
		if (l == u)
			return a[l];
		int m = (l + u) / 2;

		/* 求横跨左右的最大连续子序列左半部分 */
		int lmax = a[m], lsum = 0;
		for (int i = m; i >= l; i--) {
			lsum += a[i];
			if (lsum > lmax)
				lmax = lsum;
		}

		/* 求横跨左右的最大连续子序列右半部分 */
		int rmax = a[m + 1], rsum = 0;
		for (int i = m + 1; i <= u; i++) {
			rsum += a[i];
			if (rsum > rmax)
				rmax = rsum;
		}
		return max3(lmax + rmax, maxsequence2(a, l, m), maxsequence2(a, m + 1, u)); // 返回三者最大值
	}

	/* 求三个数最大值 */
	private static int max3(int i, int j, int k) {
		if (i >= j && i >= k)
			return i;
		return max3(j, k, i);
	}

}
