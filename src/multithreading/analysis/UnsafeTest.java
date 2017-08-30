package multithreading.analysis;

import java.lang.reflect.Field;

import org.junit.Test;

import sun.misc.Unsafe;

public class UnsafeTest {

	static class TT {
		private int count = 0;
		public TT() {
			count = 1<<5;
		}
		@Override
		public String toString() {
			return "count: "+count;
		}
	}
	@Test
	public void test() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe us = (Unsafe) f.get(null);
		System.out.println(us.addressSize());
		System.out.println(us.pageSize());
		TT t1 = new TT();
		TT t2 = TT.class.newInstance();
		TT t3 = (TT) us.allocateInstance(TT.class);
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		
	}
	static class Guard {
		private int pwd = 1;
		
		public boolean login(){
			return 88 == pwd;
		}
	}
	@Test
	public void test2() throws Exception{
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe us = (Unsafe) f.get(null);
		Guard g = new Guard();
		System.out.println(g.login());
		Field ff = g.getClass().getDeclaredField("pwd");
		us.putInt(g, us.objectFieldOffset(ff), 88);
		System.out.println(g.login());
	}
}
