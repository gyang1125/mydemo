package com.ygl.java8;

/**
 * 
 * Lambda 的本质就是就是针对功能性接口中的方法的一中实现机制.<br>
 * 相比较老得方式 <br>
 * FunctionInterface func = new FunctionImpl() { R method(T t){expression}<br>
 * 其lambda的方式更加简便 t -> expression
 * 
 *
 */
public class Lambda {

	public static void main(String[] args) {
		MyFunc<Integer, Integer> funcJava8 = x -> x * x;

		MyFunc<Integer, Integer> funcJava7 = new MyFunc<Integer, Integer>() {
			@Override
			public Integer getValue(Integer num) {
				return num * num;
			}
		};

		Integer resultJava8 = operation(25, funcJava8);
		System.out.println(resultJava8);

		Integer ressultJava7 = operation(43, funcJava7);
		System.out.println(ressultJava7);

	}

	private static Integer operation(Integer x, MyFunc fun) {
		return (Integer) fun.getValue(x);
	}
	
	@FunctionalInterface
	public interface MyFunc<T, R> {
		public R getValue(T num);
	}

}
