package com.service;

import com.entity.Teacher;

import java.util.List;

public interface TeacherService {
    boolean addTeacher(Teacher teacher);

    List<Teacher> getTeacherList(Teacher teacher);

    boolean delete(int id);

    boolean update(Teacher teacher);

    Teacher login(Teacher teacher);

    String editPassword(Teacher teacher,String newPassword);
}
