package multithreading.test;

public class CpuTest {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024));
	}
}
