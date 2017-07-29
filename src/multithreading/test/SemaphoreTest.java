package multithreading.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
	private static ExecutorService exec = Executors.newFixedThreadPool(300);
	private static Semaphore sp = new Semaphore(10);
	private static CyclicBarrier cb = new CyclicBarrier(300,new Runnable() {
		
		@Override
		public void run() {
			System.out.println("over!");
		}
	});
	public static void main(String[] args) {
		for(int i=0;i<300;i++){
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						sp.acquire();
						TimeUnit.MILLISECONDS.sleep(50);
						sp.release();
						System.out.println(sp.availablePermits());
						cb.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}); 
		}
		exec.shutdown();
	}
	
	static class Profile {
		static final ThreadLocal<Long> tl = new ThreadLocal<Long>(){
			@Override
			protected Long initialValue() {
				return System.currentTimeMillis();
			}
		};
		
		public static final void start(){
			tl.set(System.currentTimeMillis());
		}
		
		public static final Long end(){
			return System.currentTimeMillis()-tl.get();
		}
	}
	
}
