package com.xupt.demo.service;


import com.xupt.demo.entity.Course;

import java.util.List;

public interface CourseService {

    List<String> returnAllCourseName();

    int getCourseId(String courseName);
}
