package com.service.impl;

import com.dao.SelectedCourseDao;
import com.entity.Course;
import com.entity.SelectedCourse;
import com.entity.Student;
import com.service.SelectedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelectedCourseServiceImpl")
public class SelectedCourseServiceImpl implements SelectedCourseService {
    @Autowired
    private SelectedCourseDao selectedCourseDao;

    /**
     * 学生选择新的课程
     * @param selectedCourse
     * @return
     */
    @Override
    public boolean addSelectedCourse(SelectedCourse selectedCourse){
        int flag = selectedCourseDao.addSelectedCourse(selectedCourse);
        return flag!=0;
    }

    /**
     * 更新选课新鞋
     * @param selectedCourse
     * @return
     */
    @Override
    public boolean updateSelectedCourse(SelectedCourse selectedCourse){
        int flag = selectedCourseDao.updateSelectedCourse(selectedCourse);
        return flag!=0;
    }

    /**
     * 按照条件来查找选课信息
     * @param selectedCourse
     * @return
     */
    @Override
    public List<SelectedCourse> getSelectedCourseList(SelectedCourse selectedCourse){
        //如果Student_id和Course_id都为空的话
        if (selectedCourse.getStudent_id()==0 && selectedCourse.getCourse_id()==0){
            return selectedCourseDao.findAllSelectedCourse();
        }

        //如果Student_id不为空，而Course_id为空的话
        if (selectedCourse.getStudent_id()!=0 && selectedCourse.getCourse_id()==0){
            return selectedCourseDao.findSelectCourseByStudentId(selectedCourse);
        }

        //如果Student_id为空，而Course_id不为空的话
        if (selectedCourse.getStudent_id()==0 && selectedCourse.getCourse_id()!=0){
            return selectedCourseDao.findSelectCourseByCourseId(selectedCourse);
        }

        //如果Student_id和Course_id都不为空的话
        if (selectedCourse.getStudent_id()!=0 && selectedCourse.getCourse_id()!=0){
            return selectedCourseDao.findSelectCourseByStudentIdAndCourseId(selectedCourse);
        }
        return null;
    }

    /**
     * 判断是否选择了这门课程
     * @param selectedCourse
     * @return
     */
    @Override
    public boolean isSelected(SelectedCourse selectedCourse){
        return !selectedCourseDao.findSelectCourseByStudentIdAndCourseId(selectedCourse).isEmpty();
    }

    /**
     * 按照id来删除课程
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id){
        int flag = selectedCourseDao.delete(id);
        return flag!=0;
    }

    /**
     * 查找选择了该课程的学生的信息
     * @param course
     * @return
     */
    @Override
    public List<Student> getSelectedCourseStudentList(Course course){
        return selectedCourseDao.getSelectedCourseStudentList(course);
    }
}
