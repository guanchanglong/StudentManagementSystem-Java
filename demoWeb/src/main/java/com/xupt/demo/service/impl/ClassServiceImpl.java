package com.xupt.demo.service.impl;

import com.xupt.demo.dao.ClassDao;
import com.xupt.demo.dao.StudentDao;
import com.xupt.demo.dao.StudentsDao;
import com.xupt.demo.entity.StudentClass;
import com.xupt.demo.entity.Students;
import com.xupt.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClassServiceImpl")
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassDao classDao;
    @Autowired
    private StudentsDao studentsDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public int returnClassId(String className){
        return classDao.findClassByClassName(className);
    }

    @Override
    public List<StudentClass> findAll(){
        return classDao.findAll();
    }

    @Override
    public Page<StudentClass> getClassesListPage(int pageNum, int pageSize) {
        Sort.Order sort = new Sort.Order(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sort));
        return classDao.findAll(pageable);
    }

    @Override
    public List<StudentClass> findAllClassByName(String name){
        return classDao.findAllByName(name);
    }

    /**
     * 删除班级
     * 删除班级的话要连带删除班级对应的学生
     * @param id 班级id
     * @return
     */
    @Override
    public boolean deleteClassByIdAndName(int id,String name){
        int flag1 = studentsDao.deleteByClassName(name);
        int flag2 = studentDao.deleteByClassId(id);
        int flag3 = classDao.deleteClassById(id);
        return flag1 != 0 && flag2 != 0 && flag3 != 0;
    }

    @Override
    public boolean modifyClass(int id,String oldName,String name,String info){
        int flag1 = 0;
        int flag2 = 0;
        //新的班级名字和旧的班级名字不一样
        if (!oldName.equals(name)){
            flag1 = studentsDao.modifyClassName(name,oldName);
        }else{
            flag1 = 1;
        }
        flag2 = classDao.modifyClassById(id,name,info);
        return flag1!=0&&flag2!=0;
    }

    @Override
    public boolean addClass(String name,String info){
        List<StudentClass> list = classDao.findAllByName(name);
        //如果该班级不存在
        if (list.isEmpty()){
            StudentClass studentClass = new StudentClass();
            studentClass.setName(name);
            studentClass.setInfo(info);
            classDao.save(studentClass);
            return true;
        }else{
            return false;
        }
    }
}
