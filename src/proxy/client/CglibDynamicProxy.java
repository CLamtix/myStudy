package proxy.client;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import proxy.UserDaoIntercepter;
import proxy.dao.impl.UserDAOImpl;

public class CglibDynamicProxy {
	public static void main(String[] args) {
		//此代码的意思是将运行过程中创建的代理类持久化到磁盘目标目录
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\cache");
		
		//1.创建一个拦截器
		UserDaoIntercepter userDaoIntercepter = new UserDaoIntercepter();
		//2.创建Enhancer对象,作为cglib代理的入口
		Enhancer enhancer = new Enhancer();
		//3.设置将要代理的对象信息
		enhancer.setSuperclass(UserDAOImpl.class);
		//4.设置所对应的拦截器,
		enhancer.setCallback(userDaoIntercepter);
		//5.创建一个代理类
		UserDAOImpl userDaoProxy = (UserDAOImpl) enhancer.create();
		
		Object obj = userDaoProxy.findById("001");
		System.out.println(obj);

	}
}
