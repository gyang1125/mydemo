package com.ygl.pattern2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int distFromOrigin() {
		// ignoring sqrt
		return (x * x) + (y * y);
	}
}

/**
 * 
 * <p>
 * 模式2
 * </p>
 * 
 * 如果我们需要处理n个元素中：顶部/最大/最小/最接近的k个元素时，我们就可以用堆来解决了。
 * 
 * @formatter:off
 *         Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (p2.distFromOrigin() - p1.distFromOrigin());
            }
        };
   @@formatter:on
 */
class KClosestPointsToOrigin {

	public static List<Point> findClosestPoints(Point[] points, int k) {
		// 这里巧妙的使用lambda的方式传入了一个compare对象，具体看上面注释。
		PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
		// put first 'k' points in the max heap
		// 先加两个数进堆
		for (int i = 0; i < k; i++)
			maxHeap.add(points[i]);

		// go through the remaining points of the input array, if a point is closer to
		// the origin than the top point of the max-heap, remove the top point from
		// heap and add the point from the input array
		// 然后从第三个数开始和堆顶比较，替换，堆内数目一直保持两个。peek是只取不删除，poll是取出后删除。
		for (int i = k; i < points.length; i++) {
			if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) { // 如果新数据小于（离原点更近）堆顶数据
				maxHeap.poll(); // 删除堆顶数据
				maxHeap.add(points[i]); // 把新数加进堆中
			}
		}

		// the heap has 'k' points closest to the origin, return them in a list
		return new ArrayList<>(maxHeap);
	}

	public static void main(String[] args) {
		Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
		List<Point> result = KClosestPointsToOrigin.findClosestPoints(points, 2);
		System.out.print("Here are the k points closest the origin: ");
		for (Point p : result)
			System.out.print("[" + p.x + " , " + p.y + "] ");
	}
}