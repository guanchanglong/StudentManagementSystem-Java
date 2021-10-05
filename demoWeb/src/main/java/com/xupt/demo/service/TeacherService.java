package com.xupt.demo.service;


import com.xupt.demo.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> login(String name,String password);

    int modifyPassword(String name,String password);
}
