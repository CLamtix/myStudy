package proxy.dao.impl;

import proxy.dao.IUserDAO;
import proxy.model.User;

public class UserDAOImpl implements IUserDAO {

	public User findById(String id) {
		User user = null;
		if("001".equals(id)){
			user = new User("lucy");
		}
		return user;
	}

	@Override
	public String deleteById(String id) {
		String result = "";
		if("001".equals(id)){
			result = "success!";
		}
		return result;
	}
	
}
