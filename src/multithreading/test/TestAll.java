package multithreading.test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.results.PrintableResult;

/**
 * ���߳���Ҫ��дһЩ������Сdemo
 * @author clamtix
 *
 */
public class TestAll {
	private static Object o = new Object();
	
	/**
	 * ���淽ʽ�����̵߳��ػ��߳�����,ֻ�����̻߳�û��������ʱ������
	 * @throws InterruptedException
	 */
	@Test
	public void test1() throws InterruptedException{
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" isDeamon: "+Thread.currentThread().isDaemon());
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						System.out.println(Thread.currentThread().getName()+"isDeamon: "+Thread.currentThread().isDaemon());
					}
				}).start();
				
			}
		});
		t1.setDaemon(true);
		t1.start();
//		Thread.sleep(500);
	}
	
	/**
	 * ����ThreadFactory�����̵߳��ػ��߳�����,Ҳ��ֻ�����̻߳�û��������ʱ������
	 */
	@Test
	public void test2() {
		ThreadFactory tf = new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		};
		ExecutorService exec = Executors.newSingleThreadExecutor(tf);
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" isDeamon: "+Thread.currentThread().isDaemon());
			}
		});
	}
	
	/**
	 * ʹ��ThreadMXBean
	 */
	@Test
	public void test3(){
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadinfos = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo info : threadinfos){
			System.out.println("["+info.getThreadId()+"]"+info.getThreadName());
		}
		/*
			console:
			[4]Signal Dispatcher �ַ������͸�jvm�źŵ��߳� 
			[3]Finalizer ���ö����finalize�������߳�
			[2]Reference Handler ���Reference���߳�
			[1]main �û���������
		 */
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	@Test
	public void	test4() throws IOException{
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream();
		out.connect(in);
		Thread t = new Thread(new Print(in),"PrintThread");
		t.start();
		int receive = 0;
		while((receive=System.in.read())!=0){
			out.write(receive);
		}
		
		
	}
	
	static class Print implements Runnable {
		private PipedInputStream in;
		public Print(PipedInputStream in) {
			this.in = in;
		}
		@Override
		public void run() {
			int receive = 0;
			try {
				while((receive=in.read())!=0){
					System.out.print((char)receive);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��֤�߳���ֹ��ʱ���Ƿ����������notifyAll����
	 * @throws InterruptedException
	 */
	@Test
	public void test5() throws InterruptedException{
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
//						break;
					}
					
				}
			}
		});
		t.start();
		synchronized (t) {
			t.interrupt();
			t.wait();
		}
		System.out.println("determine");
	}
	
	/**
	 * ThreadLocalʹ��
	 * @throws InterruptedException 
	 */
	@Test
	public void test6() throws InterruptedException{
		Profile.begin();
		TimeUnit.MILLISECONDS.sleep(1000);
		System.out.println(Profile.end());
		
	}
	static class Profile{
		private static final ThreadLocal<Long> local = new ThreadLocal<Long>(){
			@Override
			protected Long initialValue() {
				return System.currentTimeMillis();
			}
		};
		
		public static final void begin(){
			local.set(System.currentTimeMillis());
		}
		public static final Long end(){
			return System.currentTimeMillis() - local.get();
		}
	}
}
