package xy20170609;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {
	public static Lock lock = new ReentrantLock();
	public static volatile boolean flag = true;
	@Test
	public void test1(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				LockTest.lock.lock();
				while(flag && !Thread.currentThread().isInterrupted()){
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					System.out.println("准备通过Lock释放锁!");
				} finally{
					LockTest.lock.unlock();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(LockTest.lock.tryLock()){
					flag = false;
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				System.out.println("得到了锁");
			}
		}).start();
		
	}
	
}
