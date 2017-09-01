package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrintObjMessage {
	public static void main(String[] args) {
		Class clazz = null;
		try {
			clazz = Class.forName("reflection.User");
			System.out.println(clazz.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		Field[] fields = clazz.getDeclaredFields();
		for(Field s:fields){
			System.out.println(s.toString());
		}
		
		Method[] methods = clazz.getDeclaredMethods();
		for(Method m:methods){
			System.out.println(m.toString());
		}
		
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for(Constructor c:constructors){
			System.out.println(c.toString());
		}
		try {
			User u = (User) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
}
