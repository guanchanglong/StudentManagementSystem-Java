package com.service.impl;

import com.dao.CourseDao;
import com.entity.Course;
import com.service.CourseService;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    /**
     * 添加新的课程
     * @param course
     * @return
     */
    @Override
    public boolean addCourse(Course course){
        int flag = courseDao.addCourse(course);
        return flag!=0;
    }

    /**
     * 查找课程
     * @param course
     * @return
     */
    @Override
    public List<Course> getCourseList(Course course){
        //如果Teacher_id和Name都为空的话
        if (course.getTeacher_id()==0 && StringUtil.isEmpty(course.getName())){
            return courseDao.findAllCourse();
        }

        //如果Teacher_id不为空，而Name为空的话
        if (course.getTeacher_id()!=0 && StringUtil.isEmpty(course.getName())){
            return courseDao.findCourseByTeacherId(course);
        }

        //如果Teacher_id为空，而Name不为空的话
        if (course.getTeacher_id()==0 && !StringUtil.isEmpty(course.getName())){
            String name = course.getName();
            name = "%"+name+"%";
            course.setName(name);
            return courseDao.findCourseByName(course);
        }

        //如果Teacher_id和Name都不为空的话
        if (course.getTeacher_id()!=0 && !StringUtil.isEmpty(course.getName())){
            String name = course.getName();
            name = "%"+name+"%";
            course.setName(name);
            return courseDao.findCourseByNameAndTeacherId(course);
        }
        return null;
    }

    /**
     * 按照课程id删除课程
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id){
        int flag = courseDao.delete(id);
        return flag!=0;
    }

    /**
     * 更新课程数据
     * @param course
     * @return
     */
    @Override
    public boolean update(Course course){
        int flag = courseDao.update(course);
        return flag!=0;
    }

    /**
     * 判断对应选课人数是否达到上限
     * @param courseId 对应课程id
     * @return
     */
    @Override
    public boolean selectedEnable(int courseId){
        boolean flag = true;
        List<Course> courseList = courseDao.selectedEnable(courseId);
        for(Course course:courseList){
            int max_student_num = course.getMax_student_num();
            int selected_num = course.getSelected_num();
            if(selected_num >= max_student_num){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 更新对应课程的选课人数
     * @param courseId
     * @param num
     * @return
     */
    @Override
    public boolean updateSelectedNum(int courseId,int num){
        int flag;
        //小于0就是退课
        if (num<0){
            flag = courseDao.subtractSelectedNum(courseId,1);
        }
        //大于0就是选课
        else{
            flag = courseDao.addSelectedNum(courseId,1);
        }
        return flag!=0;
    }

}
