package proxy.client;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import proxy.PersonIntercepter;
import proxy.model.Person;

public class CglibDynamicProxy {
	public static void main(String[] args) {
//		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\");
		/*
		UserDaoIntercepter userDaoIntercepter = new UserDaoIntercepter();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserDAOImpl.class);
		enhancer.setCallback(userDaoIntercepter);
		
		UserDAOImpl userDaoProxy = (UserDAOImpl) enhancer.create();
		Object obj = userDaoProxy.findById("001");
		System.out.println(obj);*/
		
		PersonIntercepter personIntercepter = new PersonIntercepter();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Person.class);
		enhancer.setCallback(personIntercepter);
		
		Person personProxy = (Person) enhancer.create();
	}
}
