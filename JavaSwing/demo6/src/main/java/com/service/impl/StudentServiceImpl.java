package com.service.impl;

import com.dao.StudentDao;
import com.entity.Student;
import com.service.StudentService;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * 添加新的学生
     * @param student
     * @return
     */
    @Override
    public boolean addStudent(Student student){
        int flag = studentDao.addStudent(student);
        return flag!=0;
    }

    /**
     * 按照条件查找学生信息
     * @param student
     * @return
     */
    @Override
    public List<Student> getStudentList(Student student){
        //如果name和classId都为空的话
        if (StringUtil.isEmpty(student.getName()) && student.getClassId()==0){
            return studentDao.findAllStudent();
        }

        //如果name不为空而classId为空的话
        if (!StringUtil.isEmpty(student.getName()) && student.getClassId()==0){
            String name = student.getName();
            name = "%"+name+"%";
            student.setName(name);
            return studentDao.findStudentByName(student);
        }

        //如果name为空而classId不为空的话
        if (StringUtil.isEmpty(student.getName()) && student.getClassId()!=0){
            return studentDao.findStudentByClassId(student);
        }

        //如果name和classId都不为空的话
        if (!StringUtil.isEmpty(student.getName()) && student.getClassId()!=0){
            String name = student.getName();
            name = "%"+name+"%";
            student.setName(name);
            return studentDao.findStudentByNameAndClassId(student);
        }

        return null;
    }

    /**
     * 按照学生id删除学生信息
     * @param id
     * @return
     */
    public boolean delete(int id){
        //关闭外键约束检查
        studentDao.setUnUsingForeignKey();
        int flag = studentDao.delete(id);
        //启用外键约束检查
        studentDao.setUsingForeignKey();
        return flag!=0;
    }

    /**
     * 按照学生id更新学生信息
     * @param student
     * @return
     */
    public boolean update(Student student){
        int flag = studentDao.update(student);
        return flag!=0;
    }

    /**
     * 修改学生密码
     * @param student
     * @param newPassword
     * @return
     */
    @Override
    public String editPassword(Student student,String newPassword){
        String result;
        List<Student> studentList = studentDao.findStudentByIdAndPassword(student);
        if (studentDao.findStudentByIdAndPassword(student).isEmpty()){
            return "旧密码错误！";
        }
        int id = studentList.get(0).getId();
        result = "修改失败！";
        int flag = studentDao.updatePassword(id,newPassword);
        if (flag!=0){
            result = "密码修改成功！";
        }
        return result;
    }

    /**
     * 学生登录
     * @param student
     * @return
     */
    public Student login(Student student){
        return studentDao.findStudentByNameAndPassword(student).get(0);
    }
}
