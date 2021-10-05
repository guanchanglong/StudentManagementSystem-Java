package com.xupt.demo.service;


import com.xupt.demo.entity.StudentClass;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassService {
    int returnClassId(String className);

    List<StudentClass> findAll();

    Page<StudentClass> getClassesListPage(int pageNum, int pageSize);

    List<StudentClass> findAllClassByName(String name);

    boolean deleteClassByIdAndName(int id,String name);

    boolean modifyClass(int id,String oldName,String name,String info);

    boolean addClass(String name,String info);
}
