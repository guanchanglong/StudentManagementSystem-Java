package com.service;

import com.entity.Student;

import java.util.List;

public interface StudentService {
    boolean addStudent(Student student);

    List<Student> getStudentList(Student student);

    boolean delete(int id);

    boolean update(Student student);

    String editPassword(Student student,String newPassword);

    Student login(Student student);
}
