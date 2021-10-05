package com.xupt.demo.service.impl;

import com.xupt.demo.dao.ClassDao;
import com.xupt.demo.dao.CourseDao;
import com.xupt.demo.entity.Course;
import com.xupt.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<String> returnAllCourseName(){
        return courseDao.findAllReturnName();
    }

    @Override
    public int getCourseId(String courseName){
        List<Integer> courseId = courseDao.findCourseIdByName(courseName);
        return courseId.get(0);
    }
}
