package com.service.impl;

import com.dao.TeacherDao;
import com.entity.Teacher;
import com.service.TeacherService;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /**
     * 添加新的教师信息
     * @param teacher
     * @return
     */
    @Override
    public boolean addTeacher(Teacher teacher){
        int flag = teacherDao.addTeacher(teacher);
        return flag!=0;
    }

    /**
     * 按条件查找教师信息
     * @param teacher
     * @return
     */
    public List<Teacher> getTeacherList(Teacher teacher){
        //如果教师姓名不为空的话
        if (!StringUtil.isEmpty(teacher.getName())){
            String name = teacher.getName();
            name = "%"+name+"%";
            teacher.setName(name);
            return teacherDao.findTeacherByName(teacher);
        }else{
            return teacherDao.findAllTeacher();
        }
    }

    /**
     * 按照id删除教师信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id){
        int flag = teacherDao.delete(id);
        return flag!=0;
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    @Override
    public boolean update(Teacher teacher){
        int flag = teacherDao.update(teacher);
        return flag!=0;
    }

    /**
     * 教师登录
     * @param teacher
     * @return
     */
    public Teacher login(Teacher teacher){
        return teacherDao.findTeacherByNameAndPassword(teacher).get(0);
    }

    /**
     * 修改教师账号密码
     * @param teacher
     * @param newPassword
     * @return
     */
    @Override
    public String editPassword(Teacher teacher,String newPassword){
        String result;
        List<Teacher> teacherList = teacherDao.findTeacherByNameAndPassword(teacher);
        if (teacherList.isEmpty()){
            return "旧密码错误！";
        }
        int id = teacherList.get(0).getId();
        int flag = teacherDao.editPassword(id,newPassword);
        if (flag!=0){
            result = "密码修改成功！";
        }else{
            result = "修改失败！";
        }
        return result;
    }
}
