package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class PrintObjMessage {
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class clazz = Class.forName("reflection.User");
		Method[] methods = clazz.getDeclaredMethods();
		for(Method m:methods){
			System.out.println("method:"+m.toString());
		}
		
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for(Constructor c:constructors){
			System.out.println("constructor:"+c.toString());
		}
		
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void testFieldClass() throws Exception{
		Class clazz = Class.forName("reflection.User");
		System.out.println("className:"+clazz.getName());
		User user = (User) clazz.newInstance();
		Field[] declaredFields = clazz.getDeclaredFields();
		for(Field s:declaredFields){
			System.out.println("declaredField:"+s.toString());
		}
		Field[] fields = clazz.getFields();
		for(Field s:fields){
			System.out.println("field:"+s.toString());
		}
		Field f = clazz.getDeclaredField("name");
		System.out.println("userName:"+f.getName());
		System.out.println("declareingClass:"+f.getDeclaringClass());
		System.out.println("this field modifiers:"+f.getModifiers());
		
		f.setAccessible(true);
		f.set(user, "df");
		System.out.println(user.getName());
		
		System.out.println("f.getType():"+f.getType());
		
	}
	
	@Test
	public void testMethodClass() throws Exception{
		Class clazz = Class.forName("reflection.User");
		User user = (User)clazz.newInstance();
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for(Method m:declaredMethods){
			System.out.println(m.getName());
		}
		System.out.println("----------------");
		Method[] methods = clazz.getMethods();
		for(Method m : methods){
			System.out.println(m.getName());
		}
		System.out.println("----------------");
		Method m = clazz.getDeclaredMethod("getAge");
		System.out.println(m.getReturnType());
		Method m1 = clazz.getDeclaredMethod("setAge", Integer.class);
		m1.invoke(user, new Integer(23));
		System.out.println(m.invoke(user));
	}
	
	@Test
	public void testClass() throws Exception{
		System.out.println(int[].class.getName());
		System.out.println(double[].class.getName());
		System.out.println(float[].class.getName());
		System.out.println(User[].class.getName());
		
		Class clazz = User.class;
		User user = new User();
		System.out.println(clazz.isInterface());
		System.out.println(clazz.isInstance(user));
		System.out.println(clazz.isArray());
		System.out.println(clazz.isPrimitive());
		Constructor c = clazz.getConstructor(String.class,Integer.class,Boolean.class);
		System.out.println(c.getName());
		System.out.println("-------------");
		Class<?> c1 = new User().new User1().getClass().getDeclaringClass();
		System.out.println(c1);
		Class<?>[] classes = clazz.getDeclaredClasses();
		for(Class c2 :classes){
			System.out.println(c2.getName());
		}
	}
	
}
