package com.ygl.java8;

import java.util.HashMap;
import java.util.Map;

public class MapForeach {

	public static void main(String[] args) {
		Map<String, Integer> items = new HashMap<>();
		items.put("A", 10);
		items.put("B", 20);
		items.put("C", 30);
		items.put("D", 40);
		items.put("E", 50);
		items.put("F", 60);

		printMapJava8(items);
//		printMapJava7(items);

	}

	private static void printMapJava8(Map<String, Integer> items) {
		items.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));

		items.forEach((k, v) -> {
			System.out.println("Item : " + k + " Count : " + v);
			if ("E".equals(k)) {
				System.out.println("Hello E");
			}
		});
	}

	/**
	 * Java7 传统的方式，获取Map的健-值对
	 */
	private static void printMapJava7(Map<String, Integer> items) {
		for (Map.Entry<String, Integer> entry : items.entrySet()) {
			System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
		}
	}

}
