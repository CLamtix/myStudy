package proxy.client;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistClassGenerator extends ClassLoader {
	
	@Test
	public void generateClassToDisk() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.Plan1");
		CtMethod method = CtNewMethod.make("public void doSomeThing(){}", cc);
		//���뷽������
		method.insertBefore("System.out.println(\"i'm working today!\");");
		cc.addMethod(method);
		cc.writeFile("F:/git/study/class_temp");
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void loadClass() throws Exception{
		File file = new File("F:/git/study/class_temp/com/Plan1.class");
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fis.read(bytes);
		fis.close();
		Class<?> clazz = new JavassistClassGenerator().defineClass("com.Plan1", bytes, 0, bytes.length);
		Method m = clazz.getMethod("doSomeThing");
		m.invoke(clazz.newInstance());
	}
	
	@Test
	public void updateClass() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		FileInputStream fis = new FileInputStream(new File("F:/git/study/class_temp/com/Plan1.class"));
		CtClass cc = pool.makeClass(fis);
		//����������
		CtMethod method = CtNewMethod.make("public void added(){}", cc);
		//��д���巽��
		method.insertBefore("System.out.println(\"this is a new method!\");");
		//��д�õķ��������class
		cc.addMethod(method);
		//��������
		CtField name = CtField.make("public String name;", cc);
		//�������Զ�Ӧ��setter��getter����
		cc.addMethod(CtNewMethod.setter("setName", name));
		cc.addMethod(CtNewMethod.getter("getName", name));
		//�����������������class
		cc.addField(name, Initializer.constant(""));

		byte[] bytes = cc.toBytecode();
		Class<?> clazz = new JavassistClassGenerator().defineClass("com.Plan1", bytes, 0, bytes.length);
		Object obj = clazz.newInstance();
		
		Method m1 = clazz.getMethod("added");
		m1.invoke(clazz.newInstance(), null);
		
		Method m2 = clazz.getMethod("setName",new Class[]{String.class});
		m2.invoke(obj, "lxy");
		
		Method m3 = clazz.getMethod("getName");
		Object o = m3.invoke(obj);
		System.out.println(o);
	}
}
