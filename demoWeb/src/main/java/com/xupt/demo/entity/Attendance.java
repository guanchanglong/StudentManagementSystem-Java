package com.xupt.demo.entity;

import lombok.Data;

/**
 *签到表
 */
@Data
public class Attendance {
	private int id;
	//学生表id
	private int student_id;
	//课程表id
	private int course_id;
	//签到日期
	String attendance_date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getAttendance_date() {
		return attendance_date;
	}
	public void setAttendance_date(String attendance_date) {
		this.attendance_date = attendance_date;
	}
	
}
