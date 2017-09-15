package proxy.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import com.sun.corba.se.pept.encoding.OutputObject;

import proxy.UserDaoHandler;
import proxy.dao.IUserDAO;
import proxy.dao.impl.UserDAOImpl;
import sun.misc.ProxyGenerator;

public class jdkDynamicProxy {
	private static IUserDAO userDao = new UserDAOImpl();
	public static void main(String[] args) throws Exception {
		
		InvocationHandler userDaoHandle = new UserDaoHandler(userDao);
		IUserDAO userDao = (IUserDAO) Proxy.newProxyInstance(UserDAOImpl.class.getClassLoader(),
															UserDAOImpl.class.getInterfaces(),
															userDaoHandle);
//		Object result = userDao.findById("001");
//		System.out.println(result.toString());
		
		Class clazz = Proxy.getProxyClass(UserDAOImpl.class.getClassLoader(), UserDAOImpl.class.getInterfaces());
		System.out.println("代理类 :"+clazz.toString());
//		IUserDAO userDao1 = (IUserDAO) clazz.getConstructor(InvocationHandler.class).newInstance(userDaoHandle);
//		Object result = userDao1.deleteById("001");
		saveInDisk();
	}
	
	/**
	 * 保存代理对象的class对象到硬盘
	 * @throws Exception
	 */
	public static void saveInDisk() throws Exception{
		byte[] bytes = ProxyGenerator.generateProxyClass("UserDaoImpl$Proxy0",UserDAOImpl.class.getInterfaces());
		FileOutputStream fos = new FileOutputStream("C:\\Users\\mxipjs\\Desktop\\UserDaoImpl$Proxy0.class");
		fos.write(bytes);
		fos.flush();
	}
}
