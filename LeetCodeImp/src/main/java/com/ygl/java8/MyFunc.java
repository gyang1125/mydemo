package com.ygl.java8;

@FunctionalInterface
public interface MyFunc<T, R> {
	public R getValue(T num);
}
