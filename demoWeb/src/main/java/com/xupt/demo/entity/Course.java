package com.xupt.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *课程信息实体
 */
@Data
@Entity
@Table(name = "s_course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	//任课老师id
	private int teacher_id;
	//该课程的人数上限
	private int max_student_num;
	private String info;
	//选课人数
	private int selected_num = 0;

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
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getMax_student_num() {
		return max_student_num;
	}
	public void setMax_student_num(int max_student_num) {
		this.max_student_num = max_student_num;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getSelected_num() {
		return selected_num;
	}
	public void setSelected_num(int selected_num) {
		this.selected_num = selected_num;
	}
	public String toString(){
		return this.name;
	}
}
