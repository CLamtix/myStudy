package multithreading.analysis;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableTest {
	public static volatile boolean isGoing = true;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable c = new C();
		FutureTask<Integer> result = new FutureTask<Integer>(c);
		new Thread(result).start();
		isGoing = false;
		System.out.println(result.get());
	}
}

class C implements Callable{
	@Override
	public Integer call() throws Exception {
		while(!Thread.currentThread().isInterrupted() && CallableTest.isGoing){
			TimeUnit.MILLISECONDS.sleep(3000);
		}
		return new Integer(1);
	}
}
