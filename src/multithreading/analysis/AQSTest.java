package multithreading.analysis;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * n个线程,每个线程打印的数字不一样,必须按顺序打印
 * @author mxipjs
 *
 */
public class AQSTest {
	private static ReentrantLock rtl = new ReentrantLock(); 
	private static Queue<Condition> queue = new LinkedList<Condition>();
	
	@Test
	public void test() throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<20;i++){
			Condition con = rtl.newCondition();
			queue.offer(con);
			exec.execute(new PrintC(con,i+""));
		}
		TimeUnit.SECONDS.sleep(1);
		rtl.lock();
		try {
			PrintC.signal(queue); //开始通知第一个打印线程
		}finally {
			rtl.unlock();
		}
		exec.shutdown();
		while(Thread.activeCount()>2){
			Thread.yield();
		}
		System.out.println("over");
	}
	
	static class PrintC implements Runnable {
		private String printStr;
		private static ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
			protected Integer initialValue() {
				return new Integer(3);
			};
		};
		private Condition con;
		public PrintC(Condition con,String str) {
			this.con = con;
			this.printStr = str;
		}
		@Override
		public void run() {
			while(count.get()>0){
				rtl.lock();
				try {
					con.await();
					System.out.println(printStr);
					signal(queue);
					count.set(count.get()-1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					rtl.unlock();
				}
			}
		}
		public static void signal(Queue<Condition> queue){
			Condition tempCondition = queue.poll();
			tempCondition.signal();
			queue.offer(tempCondition);
		}
		
	}
	
	static Condition con = rtl.newCondition();
	@Test
	public void test2() throws InterruptedException{
		class C implements Runnable {
			
			@Override
			public void run() {
				rtl.lock();
				try {
					con.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					rtl.unlock();
				}
			}
		}
		new Thread(new C()).start();
		new Thread(new C()).start();
		TimeUnit.SECONDS.sleep(1);
		rtl.lock();
		System.out.println(rtl.hasWaiters(con));
		System.out.println(rtl.getWaitQueueLength(con));
	}
	
	
}












