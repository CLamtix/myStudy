package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

import util.Common;

public class UserDaoHandler implements InvocationHandler{
	private Date date;
	private Object realObj;
	private Object result;
	public UserDaoHandler() {
		// TODO Auto-generated constructor stub
	}
	public UserDaoHandler(Object obj){
		this.realObj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("调用方法时间:["+Common.df5.format(new Date())+"]");
		if("findById".equals(method.getName()) || "toString".equals(method.getName())){
			result = method.invoke(realObj, args);
		}else{
			System.out.println("调用了禁止方法:methodName["+method.getName()+"]");
		}
		System.out.println("调用方法结束!");
		return result;
	}

}
