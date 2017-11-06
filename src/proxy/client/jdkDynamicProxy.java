package proxy.client;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.junit.Test;
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
		Object result = userDao.findById("001");
		System.out.println(result.toString());
	}
	
	/**
	 * 保存代理对象的class对象到硬盘
	 * @throws Exception
	 */
	@Test
	public void saveInDisk() throws Exception{
		byte[] bytes = ProxyGenerator.generateProxyClass("UserDaoImpl$Proxy0",UserDAOImpl.class.getInterfaces());
		FileOutputStream fos = new FileOutputStream("F:\\UserDaoImpl$Proxy0.class");
		fos.write(bytes);
		fos.flush();
	}
}
