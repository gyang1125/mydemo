package com.ygl.autoboxing;

/**
 * 
 * == 的含义<br>
 * 基本数据类型之间应用双等号，比较的是他们的数值。 <br>
 * 复合数据类型(类)之间应用双等号，比较的是他们在内存中的存放地址。
 * <p>
 * Object.equals() 比较对象的内存地址
 * 
 * 5种包装类Byte,Short,Integer,Long,Character,Boolean；
 * 
 * 这5种包装类默认创建了数值[-128，127]的相应类型的缓存数据，其值放在常量池中，但是超出此范围仍然会去heap中创建新的对象。引用是放在stack中的
 * 
 * Integer value=40；Java在编译的时候会直接将代码封装成Integer
 * value=Integer.valueOf(40);，从而使用常量池中的对象。
 */
public class AutoUnboxing {
	public static final String A = "ab"; // 常量A
	public static final String B = "cd"; // 常量B

	public static final String C; // 常量C
	public static final String D; // 常量D
	static {
		C = "ab";
		D = "cd";
	}

	public static void main(String[] args) {

		IntegerTest();

		StringTest1();

		StringTest2();

		StringTest3();

		StringTest4();

		StringTest5();

	}

	/**
	 * 1. 首先的区别是，equals 是方法，而 == 是操作符； <br>
	 * 
	 * 2. 对于基本类型的变量来说（如 short、 int、 long、 float、 double），只能使用 == ，因为这些基本类型的变量没有
	 * equals 方法。对于基本类型变量的比较，使用 == 比较， 一般比较的是它们的值。 <br>
	 * 
	 * 3. 对于引用类型的变量来说（例如 String 类）才有 equals 方法，因为 String 继承了 Object 类， equals 是
	 * Object 类的通用方法。对于该类型对象的比较，默认情况下，也就是没有复写 Object 类的 equals 方法，使用 == 和 equals
	 * 比较是一样效果的，都是比较的是它们在内存中的存放地址。但是对于某些类来说，为了满足自身业务需求，可能存在 equals 方法被复写的情况，这时使用
	 * equals 方法比较需要看具体的情况，例如 String复写了Object的equals 方法，因此比较它们的值；
	 * 
	 * String.intern() 把heap的字符串放入到常量池中
	 */
	private static void StringTest5() {
		String str1 = "123";
		String str2 = "123";

		String a = new String("123");
		String b = new String("123");

		System.out.println("str1 == str2: " + (str1 == str2));// true 在String Pool中比较
		System.out.println("a == b: " + (a == b));// false 不同的对象地址在heap中
		System.out.println("a.equals(b): " + a.equals(b)); // true 比较的是值

		System.out.println(str1 == b);// false 常量对象和heap对象不同
		String b2 = b.intern(); // 把heap的字符串放入到常量池中
		System.out.println((str1 == b2));// true 赋值给常量对象，比较的是常量池中的对象
	}

	/**
	 * 这条语句创建了2个对象。
	 * 
	 * 当你new String("abc")时，其实会先在"字符串常量区"生成一个abc的对象，然后new
	 * String()时会在堆中分配空间，然后此时会把字符串常量区中abc"复制"一个给堆中的String，故abc应该在堆中和字符串常量区
	 */
	private static void StringTest4() {

		String s1 = new String("abc");
	}

	/**
	 * 字符串拼接特例1<br>
	 * final 修饰，但是没有直接赋值
	 */
	private static void StringTest3() {
		// C = "ab";
		// D = "cd";
		String s = C + D;
		String t = "abcd";
		if (s == t) {
			System.out.println("s等于t，它们是同一个对象");
		} else {
			System.out.println("SpringTest3: s不等于t，它们不是同一个对象");
		}
	}

	/**
	 * 字符串拼接特例2<br>
	 * final 修饰，直接赋值
	 */
	private static void StringTest2() {
		String s = A + B; // 将两个常量用+连接对s进行初始化
		String t = "abcd";
		if (s == t) {
			System.out.println("SprintTest4: s等于t，它们是同一个对象");
		} else {
			System.out.println("s不等于t，它们不是同一个对象");
		}
	}

	/**
	 * 字符串拼接特例3<br>
	 * 无final修饰，双引号拼接 VS 引用拼接
	 */
	private static void StringTest1() {
		String str1 = "str";
		String str2 = "ing";

		String str3 = "str" + "ing"; // 引号拼接是发生在常量池中的
		String str4 = str1 + str2; // 使用引用拼接，则会创建一个新的对象
		System.out.println(str3 == str4);// false

		String str5 = "string";
		System.out.println(str3 == str5);// true
	}

	/**
	 * Integer的自动拆箱和cache的范围
	 */
	private static void IntegerTest() {
		Integer i1 = 40; // 自动装箱成为Integer
		Integer i2 = 40;
		Integer i3 = 0;
		Integer i4 = Integer.valueOf(40);
		Integer i5 = Integer.valueOf(40);
		Integer i6 = Integer.valueOf(0);
		int i7 = 40;
		Integer i8 = 400;
		Integer i9 = 400;

		System.out.println("i1=i2   " + (i1 == i2)); // true, 没有超cache范围，比较的是常量池中的数值
		System.out.println("i1=i2+i3   " + (i1 == i2 + i3)); // true, 没有超cache范围，比较的是常量池中的数值
		System.out.println("i1=i4   " + (i1 == i4)); // false
		System.out.println("i4=i5   " + (i4 == i5)); // false
		System.out.println("i4=i5+i6   " + (i4 == i5 + i6)); // true
		System.out.println("i1=i7 " + (i1 == i7)); // true, i1自动拆箱成int类型再和i7比较
		System.out.println("i4=i7 " + (i4 == i7)); // true, i4自动拆箱成int类型再和i7比较
		System.out.println("i8=i9 " + (i8 == i9)); // false, 超过cache范围，不放常量池，而是各自new了一个新对象
	}

}
