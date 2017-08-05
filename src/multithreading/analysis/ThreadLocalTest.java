package multithreading.analysis;

import org.junit.Test;

public class ThreadLocalTest {
	
	public static ThreadLocal<String> tl = new ThreadLocal<String>(){
		protected String initialValue() {
			return "defaultValue";
		};
	};
	
	@Test
	public void test1(){
		new Thread(new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+":"+tl.get());
				tl.remove();
				System.out.println(Thread.currentThread().getName()+":"+tl.get());
			}
		},"Thread-A").start();
		
		new Thread(new Runnable() {
			public void run() {
				tl.set("Thread-B value");
				System.out.println(Thread.currentThread().getName()+":"+tl.get());
				tl.remove();
				System.out.println(Thread.currentThread().getName()+":"+tl.get());
			}
		},"Thread-B").start();
		while(Thread.activeCount()>2){
			Thread.yield();
		}
	}
}
