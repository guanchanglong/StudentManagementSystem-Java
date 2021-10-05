package com.service.impl;

import com.dao.ClassDao;
import com.entity.StudentClass;
import com.service.ClassService;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClassServiceImpl")
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    /**
     * 添加新的班级（插入班级名字和简介）
     * @param studentClass
     * @return 返回插入结果
     */
    @Override
    public boolean addClass(StudentClass studentClass){
        int flag = classDao.addClass(studentClass);
        return flag!=0;
    }

    /**
     * 显示班级信息
     * @param studentClass
     * @return
     */
    @Override
    public List<StudentClass> getClassList(StudentClass studentClass){
        //名字不为空，按姓名查找
        if(!StringUtil.isEmpty(studentClass.getName())){
            String name = studentClass.getName();
            name = "%"+name+"%";
            studentClass.setName(name);
            return classDao.findClassByName(studentClass);
        }
        //显示所有的班级信息
        else{
            return classDao.findAllClass();
        }
    }

    /**
     * 删除对应id的班级信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id){
        int flag = classDao.delete(id);
        return flag!=0;
    }

    /**
     * 按id更新班级信息
     * @param studentClass
     * @return
     */
    @Override
    public boolean update(StudentClass studentClass){
        int flag = classDao.update(studentClass);
        return flag!=0;
    }
}
