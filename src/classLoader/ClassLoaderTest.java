package classLoader;

import org.junit.Test;

public class ClassLoaderTest {
	public static void main(String[] args) {
		System.out.println("ClassLoaderTest类的加载器:"+ClassLoaderTest.class.getClassLoader());
		System.out.println("System类的加载器:"+System.class.getClassLoader());
		ClassLoader cl = ClassLoaderTest.class.getClassLoader();
		while(cl!=null){
			System.out.print(cl.getClass().getName()+"->");
			cl = cl.getParent();
		}
		System.out.println(cl);
		
	}
	
	@Test
	public void test(){
		System.out.println(ClassLoaderAttachment.class.getClassLoader());
	}
	
	@Test
	public void test2(){
		try {
			Class classDate = new MyClassLoader("E:\\git\\study\\class_temp").loadClass("ClassLoaderAttachment");
			System.out.println(classDate.getClassLoader());
			System.out.println(ClassLoaderAttachment.class.getClassLoader());
			Object ca = classDate.newInstance();
			System.out.println(ca.getClass().getClassLoader().getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
