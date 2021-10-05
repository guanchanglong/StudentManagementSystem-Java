package com.xupt.demo.service.impl;

import com.xupt.demo.dao.TeacherDao;
import com.xupt.demo.entity.Teacher;
import com.xupt.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /**
     * 教师登录
     * @return
     */
    public List<Teacher> login(String name,String password){
        return teacherDao.findAllByNameAndPassword(name,password);
    }

    /**
     * 教师修改密码
     * @param name
     * @param password
     * @return
     */
    @Override
    public int modifyPassword(String name,String password){
        return teacherDao.modifyPassword(name,password);
    }

}
