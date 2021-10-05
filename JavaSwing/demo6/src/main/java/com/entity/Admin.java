package com.entity;


import lombok.Data;

/**
 * 管理员表
 */
@Data
public class Admin{
	private int id;
	//管理员名字
	private String name;
	//管理员密码
	private String password;
	//创建日期
	private String createDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
