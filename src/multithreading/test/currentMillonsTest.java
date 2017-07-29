package multithreading.test;

import java.util.concurrent.TimeUnit;

public class currentMillonsTest {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.currentTimeMillis());
		TimeUnit.MILLISECONDS.sleep(3000);
		System.out.println(System.currentTimeMillis());
	}
}
