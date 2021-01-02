package com.ygl.exception;

/**
 * 里氏代换原则[能使用父类型的地方一定能使用子类型, 如下 
 * 
 * try {
	throw new ExampleB("b")
} catch（ExampleA e）{
	System.out.println("ExampleA");
} catch（Exception e）{
	System.out.println("Exception");
}
 * 
 * 
 * @author gyang
 *
 */
public class TestException {

	public static void main(String[] args) throws Exception {
		System.out.println(getInt1());
		System.out.println(getInt2());
		testExcpetionThrow();
	}

	/**
	 * try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？ 
	 * 会执行，在 return 前执行
	 * 
	 * @return
	 */
	public static int getInt1() {
		int a = 10;
		try {
			System.out.println(a / 0);
			a = 20;
		} catch (ArithmeticException e) {
			a = 30;
			return a;
			/*
			 * return a 在程序执行到这一步的时候，这里不是return a 而是 return 30；这个返回路径就形成了
			 * 但是呢，它发现后面还有finally，所以继续执行finally的内容，a=40 再次回到以前的路径,继续走return
			 * 30，形成返回路径之后，这里的a就不是a变量了，而是常量30
			 */
		} finally {
			a = 40;
		}
		return a;
	}

	/**
	 * return 在 finally中覆盖前面的return
	 * 
	 * @return
	 */
	public static int getInt2() {
		int a = 10;
		try {
			System.out.println(a / 0);
			a = 20;
		} catch (ArithmeticException e) {
			a = 30;
			return a;
		} finally {
			a = 40;
			// 如果这样，就又重新形成了一条返回路径，由于只能通过1个return返回，所以这里直接返回40
			return a;
		}
	}

	/**
	 * 里氏代换原则
	 * 
	 * 	Caught Annoyance
		Caught Sneeze
		Hello World!
		
	 * @throws Exception
	 */
	public static void testExcpetionThrow() throws Exception {
		try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {
				System.out.println("Caught Annoyance");
				throw a;
			}
		} catch (Sneeze s) {
			System.out.println("Caught Sneeze");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
}

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}