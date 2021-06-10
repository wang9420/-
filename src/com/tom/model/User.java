package com.tom.model;

/**
 * 模型层 用户实体
 * @author Administrator
 *
 */
public class User {

	private int id; // 编号
	private String username; // 用户名
	private String password; // 密码
    
	// 没有参数，构造函数
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 带二个参数构造方法，忽略id(自增字段）
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// 三个参数的构造函数
	public User(int id, String username, String password) { 
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}	
