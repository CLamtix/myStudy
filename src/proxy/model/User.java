package proxy.model;

public class User {
	private String id;
	private String name;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String name) {
		// TODO Auto-generated constructor stub
		this.id = "001";
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
