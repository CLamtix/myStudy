package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class PersonIntercepter implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method m, Object[] args,
			MethodProxy proxy) throws Throwable {
		Object result = null;
		System.out.println("准备执行代理方法");
		result = proxy.invokeSuper(obj, args);
		System.out.println("执行完毕");
		return result;
	}

}
