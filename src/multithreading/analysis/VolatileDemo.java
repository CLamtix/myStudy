package multithreading.analysis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class VolatileDemo {
    public static volatile boolean isStopA = false;
    public static boolean isStopB = false;
    
    public static void main(String[] args) throws InterruptedException {
          Thread a = new Thread(new Runnable() {
               public void run() {
                    while(!isStopA){}
               }
          },"Thread-A");
          Thread b = new Thread(new Runnable() {
               public void run() {
                    while(!isStopB){
//                    	System.out.print("");
                    }
               }
          },"Thread-B");
          
          a.start();
          b.start();
          TimeUnit.MILLISECONDS.sleep(100);
          isStopA = true;
          isStopB = true;
          TimeUnit.MILLISECONDS.sleep(100);
          System.out.println("Thread-A isAlive:"+a.isAlive());
          System.out.println("Thread-B isAlive:"+b.isAlive());
    }
    
    
    public static boolean initialized = false;
    public static int index = 0;
    @Test
    public void test(){
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				index = 1;
				initialized = true;
			}
		},"A").start();
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!initialized){}
				System.out.println(index);
			}
		},"B").start();
    }
    
    
    
    public static volatile int inc = 0;
    public static CountDownLatch cdl = new CountDownLatch(1);
    @Test
    public void test1() throws InterruptedException{
    	System.out.println(inc);
    	for(int i = 0;i<20;i++){
    		new Thread(new tt(i)).start();
    	}
    	cdl.countDown();
    	while(Thread.activeCount()>2){
    		Thread.yield();
    	}
    	System.out.println(inc);
    }
    static class tt implements Runnable{
		private int temp;
    	public tt(int temp) { this.temp = temp; }
		@Override
		public void run() {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			inc = temp;
		}
	}
    
    @Test
    public void test2(){
    	System.out.println(Runtime.getRuntime().availableProcessors());
    }
    
    public class MemoryModel {
    	private int count;
    	private boolean stop;
    	public void initCountAndStop() {
    		count = 1;
    		stop = false;
    	}
    	public void doLoop() {
    		while(!stop) {
    			count++;
    		}
    	}
    	public void printResult() {
    		System.out.println(count);
    		System.out.println(stop);
    	}
    }
    
    /**
     * synchronized作用于实例方法上
     */
    @Test
    public void test3(){
    	final SynObj obj = new SynObj();
    	for(int i =0;i<2000;i++){
    		new Thread(new Runnable() {
				public void run() {
					obj.incWithSyn();
				}
			}).start();
    	}
    	while(Thread.activeCount()>2){
    		Thread.yield();
    	}
    	System.out.println(obj.get());
    	/**
    	 * console:2000
    	 */
    }
    static class SynObj{
    	private int a = 0;
    	public synchronized void incWithSyn(){
    		a++;
    	}
    	public int get(){
    		return a;
    	}
    }
    
    /**
     * synchronized作用与静态方法
     */
    @Test
    public void test4(){
    	for(int i=0;i<5;i++){
    		new Thread(new Runnable() {
				public void run() {
					SynObjStatic.incWithSynFiled();
				}
			}).start();
    	}
    	while(Thread.activeCount()>2){
    		Thread.yield();
    	}
    	System.out.println(SynObjStatic.get());
    }
    static class SynObjStatic{
    	private static int b = 0;
    	public static synchronized void incWithSynFiled(){
    		b++;
    	}
    	public static int get(){
    		return b;
    	}
    }
    
    /**
     * synchronized作用与代码块
     */
    @Test
    public void test5(){
    	final SynAreaObj obj = new SynAreaObj();
    	for (int i = 0; i < 5; i++) {
	    	new Thread(new Runnable() {
				public void run() {
					obj.inc();
				}
			}).start();
    	}
    	while(Thread.activeCount()>2){
    		Thread.yield();
    	}
    	System.out.println(obj.get());
    }
    static class SynAreaObj{
    	private Object obj = new Object();
    	private int c = 0;
    	public void inc(){
    		synchronized (obj) {
    			c++;
			}
    	}
    	public int get(){
    		return c;
    	}
    }
}



















