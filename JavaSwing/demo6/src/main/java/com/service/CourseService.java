package com.service;

import com.entity.Course;

import java.util.List;

public interface CourseService {
    boolean addCourse(Course course);

    List<Course> getCourseList(Course course);

    boolean delete(int id);

    boolean update(Course course);

    boolean selectedEnable(int courseId);

    boolean updateSelectedNum(int courseId,int num);
}
