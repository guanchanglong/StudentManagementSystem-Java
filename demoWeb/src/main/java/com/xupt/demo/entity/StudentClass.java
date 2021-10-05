package com.xupt.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *学生班级信息
 */
@Data
@Entity
@Table(name = "s_class")
public class StudentClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//班级姓名
	private String name;
	//班级信息
	private String info;

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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString(){
		return this.name;
	}
}
