package multithreading.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TestAtomic {
	@Test
	public void test1(){
		AtomicInteger ai = new AtomicInteger();
		System.out.println(ai.getAndIncrement());
		System.out.println(ai.get());
		System.out.println(ai.incrementAndGet());
	}
}
