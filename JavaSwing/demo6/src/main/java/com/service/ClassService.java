package com.service;

import com.entity.StudentClass;

import java.util.List;

public interface ClassService {
    boolean addClass(StudentClass studentClass);

    List<StudentClass> getClassList(StudentClass studentClass);

    boolean delete(int id);

    boolean update(StudentClass studentClass);
}
