package com.xupt.demo.service;


import com.xupt.demo.entity.Student;
import com.xupt.demo.entity.StudentClass;
import com.xupt.demo.entity.Students;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> login(String name,String password);

    StudentClass findClassById(int classId);

    Page<Students> getStudentsListPage(int pageNum, int pageSize);

    List<Student> findAll();

    List<Students> findStudentsByName(String name);

    List<String> returnClassName();

    boolean findStudentByNameAndClassAndSex(String name,String className,String sex,String password);

    boolean deleteStudent(String name,String className,int classId,String sex);

    boolean modifyStudent(String oldName, String oldClassName, String newName, String newClassName, String newSex, String newPassword);

    boolean modifyPassword(String name,String password);
}
