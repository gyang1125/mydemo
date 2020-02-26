package com.ygl.java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8Test1 {
	public static void main(String[] args) {

		List<Developer> listDevs = getDevelopers();

		System.out.println("Before Sort");
		for (Developer developer : listDevs) {
			System.out
					.println("[" + developer.getName() + ", " + developer.getAge() + " " + developer.getSalary() + "]");
		}

		// sort by age java7
//		java7Compare(listDevs);

		// sort by age java8
//		java8Compare(listDevs);

		// lambda
		java8Lambda(listDevs);

	}

	private static void java8Lambda(List<Developer> listDevs) {
		listDevs.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());

		// java 8 only, lambda also, to print the List
		listDevs.forEach((developer) -> System.out
				.println("[" + developer.getName() + ", " + developer.getAge() + " " + developer.getSalary() + "]"));
	}

	private static void java8Compare(List<Developer> listDevs) {
		listDevs.sort(new Comparator<Developer>() {
			@Override
			public int compare(Developer o1, Developer o2) {
				return o2.getAge() - o1.getAge();
			}
		});

		printCompareResult(listDevs);
	}

	private static void printCompareResult(List<Developer> listDevs) {
		System.out.println("After Sort");
		for (Developer developer : listDevs) {
			System.out
					.println("[" + developer.getName() + ", " + developer.getAge() + " " + developer.getSalary() + "]");
		}
	}

	private static void java7Compare(List<Developer> listDevs) {
		Collections.sort(listDevs, new Comparator<Developer>() {
			@Override
			public int compare(Developer o1, Developer o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		printCompareResult(listDevs);
	}

	private static List<Developer> getDevelopers() {

		List<Developer> result = new ArrayList<Developer>();

		result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
		result.add(new Developer("alvin", new BigDecimal("80000"), 20));
		result.add(new Developer("jason", new BigDecimal("100000"), 10));
		result.add(new Developer("iris", new BigDecimal("170000"), 55));

		return result;

	}
}
