package multithreading.test;

import java.util.concurrent.TimeUnit;

public class ToolTest {
	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"timeWaitingThread").start();
		new Thread(new Waiting(),"WaitingThread").start();
		new Thread(new Blocked(),"BlockedThread1").start();
		new Thread(new Blocked(),"BlockedThread2").start();
		System.out.println("over!");
	}
	static class TimeWaiting implements Runnable{
		@Override
		public void run() {
			while(true){
				SleepUtils.second(100);
			}
		}
	}
	static class Waiting implements Runnable{
		@Override
		public void run() {
			while(true){
				synchronized (Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	static class Blocked implements Runnable{
		@Override
		public void run() {
			synchronized (Blocked.class) {
				while(true){
					SleepUtils.second(100);
				}
			}
		}
	}
	
	
	
	
	
	
	
	static class SleepUtils{

		public static void second(long i) {
			try {
				TimeUnit.MILLISECONDS.sleep(i);
			} catch (Exception e) {
			}
		}
		
	}
}
