package com.ygl.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构建Stream对象，要注意Stream.of(Array)和Arrays.stream(Element)的区别。
 * 
 * Arrays.stream(Object): 可以降维
 * 
 * <p>
 * Stream<String[]> -> flatMap -> Stream<String> <br>
 * Stream<Set<String>> -> flatMap -> Stream<String> <br>
 * Stream<List<String>> -> flatMap -> Stream<String> <br>
 * Stream<List<Object>> -> flatMap -> Stream<Object> <br>
 * </p>
 *
 */

public class StreamAndFlatmap {

	public static void main(String[] args) {

//		Example_1();

//		Exmaple_2();

		Example_3();
	}

	/**
	 * 流的迭代Stream.iterate和流的并行计算能力parallel
	 *
	 */
	private static void Example_3() {
		long count =
		// @formatter:off
				Stream.iterate(0, n -> n + 1)
				.limit(1_000).parallel()
				.filter(StreamAndFlatmap::isPrime)
				.peek(x -> System.out.format("%s\t", x))
				.count();
		// @formatter:on

		System.out.println("\nTotal: " + count);
	}

	public static boolean isPrime(int number) {
		if (number <= 1)
			return false;
		return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
	}

	/**
	 * 整型流对象IntStream
	 * 
	 */
	private static void Exmaple_2() {
		int[] intArray = { 1, 2, 3, 4, 5, 6 };

		// 1. Stream<int[]>
		Stream<int[]> streamArray = Stream.of(intArray);
		IntStream streamArray2 = Arrays.stream(intArray);

		// 2. Stream<int[]> -> flatMap -> IntStream
		IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

		intStream.forEach(x -> System.out.println(x));
	}

	/**
	 * 流降维和扁平化
	 */
	private static void Example_1() {
		String[][] data = new String[][] { { "a", "b" }, { "c", "d" }, { "e", "f" } };

		// Stream<String[]>
		Stream<String[]> stringArrayStream = Arrays.stream(data);

		Stream<String> stringStream = stringArrayStream.flatMap(x -> Arrays.stream(x));

		stringStream.forEach(System.out::println);
	}

}
