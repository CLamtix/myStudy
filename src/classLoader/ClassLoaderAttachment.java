package classLoader;

public class ClassLoaderAttachment {
	public static int a = 12;
	static{
		System.out.println("ClassLoaderAttachment类已被加载");
	}
}
