package com.ygl.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterMapFindAnyOrElse {

	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person("mkyong", 30), new Person("jack", 20),
				new Person("lawrence", 40));

		String name = persons.stream().filter(x -> "jack".equals(x.getName())).map(Person::getName) // convert stream to
																									// String
				.findAny().orElse("");

		System.out.println("name : " + name);

		List<String> collect = persons.stream().map(Person::getName).collect(Collectors.toList());

		collect.forEach(System.out::println);

	}

}

class Person {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}