package com.xupt.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_score")
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int student_id;
	private int course_id;
	private int score;

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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
