package com.xupt.demo.service.impl;

import com.xupt.demo.dao.ClassDao;
import com.xupt.demo.dao.StudentDao;
import com.xupt.demo.dao.StudentsDao;
import com.xupt.demo.entity.Student;
import com.xupt.demo.entity.StudentClass;
import com.xupt.demo.entity.Students;
import com.xupt.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private StudentsDao studentsDao;

    /**
     * 学生登录
     * @return
     */
    public List<Student> login(String name,String password){
        return studentDao.findAllByNameAndPassword(name,password);
    }


    public StudentClass findClassById(int classId){
        return classDao.findAllById(classId);
    }

    @Override
    public Page<Students> getStudentsListPage(int pageNum, int pageSize) {
        Sort.Order sort = new Sort.Order(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sort));
        return studentsDao.findAll(pageable);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Students> findStudentsByName(String name){
        return studentsDao.findAllByName(name);
    }

    @Override
    public List<String> returnClassName(){
        return classDao.findAllReturnName();
    }

    /**
     * 添加学生
     * 判断重复条件：
     *  在同一个班级、姓名重复、性别重复则为重复
     *  早知道就用学号区分了，搞得现在判断个重复还这么麻烦
     * @param name
     * @param className
     * @param sex
     * @param password
     * @return
     */
    @Override
    public boolean findStudentByNameAndClassAndSex(String name,String className,String sex,String password){
        boolean flag = false;
        int classId = classDao.findClassByClassName(className);
        List<Student> studentList = studentDao.findAllByNameAndClassIdAndSex(name,classId,sex);
        //不存在该号学生
        if (studentList.isEmpty()){
            Students students = new Students();
            Student student = new Student();
            students.setName(name);
            students.setClassName(className);
            students.setPassword(password);
            students.setSex(sex);
            //保存进用来显示的表里面
            studentsDao.save(students);
            student.setName(name);
            student.setClassId(classId);
            student.setPassword(password);
            student.setSex(sex);
            //存进学生表
            studentDao.save(student);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deleteStudent(String name,String className,int classId,String sex){
        int flag1 = 0;
        int flag2 = 0;
        //如果学生表删除成功
        flag1 = studentDao.deleteByNameAndClassIdAndSex(name,classId,sex);
        flag2 = studentsDao.deleteByNameAndClassNameAndSex(name,className,sex);
        if (flag1!=0&&flag2!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean modifyStudent(String oldName,
                                 String oldClassName,
                                 String newName,
                                 String newClassName,
                                 String newSex,
                                 String newPassword){
        int oldClassId = classDao.findClassByClassName(oldClassName);
        int newClassId = classDao.findClassByClassName(newClassName);
        int flag1 = 0;
        int flag2 = 0;
        flag1 = studentDao.modifyStudent(oldName,oldClassId,newName,newClassId,newSex,newPassword);
        flag2 = studentsDao.modifyStudents(oldName,oldClassName,newName,newClassName,newSex,newPassword);
        if (flag1!=0&&flag2!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean modifyPassword(String name,String password){
        int flag1 = 0;
        int flag2 = 0;
        flag1 = studentDao.modifyPassword(name,password);
        flag2 = studentsDao.modifyPassword(name,password);
        if (flag1!=0&&flag2!=0){
            return true;
        }else{
            return false;
        }
    }
}
