package proxy.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import proxy.UserDaoHandler;
import proxy.dao.IUserDAO;
import proxy.dao.impl.UserDAOImpl;

public class UserServiceImpl {
	private static IUserDAO userDao = new UserDAOImpl();
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		InvocationHandler userDaoHandle = new UserDaoHandler(userDao);
		IUserDAO userDao = (IUserDAO) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),
															UserDAOImpl.class.getInterfaces(),
															userDaoHandle);
//		Object result = userDao.findById("001");
//		System.out.println(result.toString());
		
		Class clazz = Proxy.getProxyClass(UserServiceImpl.class.getClassLoader(), UserDAOImpl.class.getInterfaces());
		System.out.println("¥˙¿Ì¿‡ :"+clazz.toString());
		IUserDAO userDao1 = (IUserDAO) clazz.newInstance();
		Object result = userDao.findById("001");
	}
}
