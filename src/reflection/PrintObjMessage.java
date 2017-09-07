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
	public void test() throws Exception{
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
		
	}
}
