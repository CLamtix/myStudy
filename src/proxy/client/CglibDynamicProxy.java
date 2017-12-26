package proxy.client;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import proxy.UserDaoIntercepter;
import proxy.dao.impl.UserDAOImpl;

public class CglibDynamicProxy {
	public static void main(String[] args) {
		//�˴������˼�ǽ����й����д����Ĵ�����־û�������Ŀ��Ŀ¼
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\cache");
		
		//1.����һ��������
		UserDaoIntercepter userDaoIntercepter = new UserDaoIntercepter();
		//2.����Enhancer����,��Ϊcglib��������
		Enhancer enhancer = new Enhancer();
		//3.���ý�Ҫ����Ķ�����Ϣ
		enhancer.setSuperclass(UserDAOImpl.class);
		//4.��������Ӧ��������,
		enhancer.setCallback(userDaoIntercepter);
		//5.����һ��������
		UserDAOImpl userDaoProxy = (UserDAOImpl) enhancer.create();
		
		Object obj = userDaoProxy.findById("001");
		System.out.println(obj);

	}
}
