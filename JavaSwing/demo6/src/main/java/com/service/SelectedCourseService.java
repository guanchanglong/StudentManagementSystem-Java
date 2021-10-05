package com.service;

import com.entity.Course;
import com.entity.SelectedCourse;
import com.entity.Student;

import java.util.List;

public interface SelectedCourseService {
    boolean addSelectedCourse(SelectedCourse selectedCourse);

    boolean updateSelectedCourse(SelectedCourse selectedCourse);

    List<SelectedCourse> getSelectedCourseList(SelectedCourse selectedCourse);

    boolean isSelected(SelectedCourse selectedCourse);

    boolean delete(int id);

    List<Student> getSelectedCourseStudentList(Course course);
}
