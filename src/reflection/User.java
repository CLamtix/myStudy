package reflection;

public class User {
	private String name;
	private Integer age;
	private Boolean adult;
	public User() {
		
	}
	public User(String name, Integer age, Boolean adult) {
		super();
		this.name = name;
		this.age = age;
		this.adult = adult;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getAdult() {
		return adult;
	}
	public void setAdult(Boolean adult) {
		this.adult = adult;
	}
	
	
}
