package multithreading.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestFor {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Thread t1= new Thread(new RunC(lock));
		Thread t2= new Thread(new RunC(lock));
		t1.start();
		t2.start();
	}
	static class RunC implements Runnable{
		private Lock lock ;
		public RunC(Lock lock) {
			this.lock = lock;
		}
		@Override
		public void run() {
			lock.lock();
		}
	}
}
