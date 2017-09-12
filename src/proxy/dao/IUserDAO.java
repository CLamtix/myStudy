package proxy.dao;

import proxy.model.User;

public interface IUserDAO {
	public User findById(String id);
	public String deleteById(String id);
}
