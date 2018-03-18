package mytest.basis;

import java.util.ArrayList;
import java.util.List;

public class TestSort {

	public static void main(String[] args) {
		List<Long> timestamp = new ArrayList<Long>();
		timestamp.add(1111L);
		timestamp.add(2222L);
		timestamp.add(3333L);
		timestamp.add(4444L);
		timestamp.add(5555L);
		
		timestamp.sort((t1, t2) -> {
			if(t1 == t2)
				return 0;
			return t1 > t2 ? -1 : 1;
		});
		timestamp.forEach(System.out::println);
	}
}
