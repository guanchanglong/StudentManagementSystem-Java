package com.dao;

import com.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseDao {
    /**
     * 添加新的课程
     * @param course
     * @return 返回添加结果
     */
    @Insert("insert into s_course values(null,#{course.name},#{course.teacher_id},#{course.max_student_num},#{course.info},0)")
    int addCourse(@Param("course") Course course);

    /**
     * 查找全部课程
     * @return
     */
    @Select("select * from s_course")
    List<Course> findAllCourse();

    /**
     * 按课程名称进行查找
     * @param course
     * @return
     */
    @Select("select * from s_course where name like #{course.name}")
    List<Course> findCourseByName(@Param("course") Course course);

    /**
     * 按课程任课老师的id进行查找
     * @param course
     * @return
     */
    @Select("select * from s_course where teacher_id = #{course.teacher_id}")
    List<Course> findCourseByTeacherId(@Param("course") Course course);

    /**
     * 按课程名称和任课老师id进行查找
     * @param course
     * @return
     */
    @Select("select * from s_course where name like #{course.name} and teacher_id = #{course.teacher_id}")
    List<Course> findCourseByNameAndTeacherId(@Param("course") Course course);

    /**
     * 按照课程id来删除课程
     * @param id
     * @return 删除结果
     */
    @Delete("delete from s_course where id = #{courseId}")
    int delete(@Param("courseId") int id);

    /**
     * 更新课程数据
     * @param course
     * @return
     */
    @Update("update s_course set name = #{course.name}, teacher_id = #{course.teacher_id}, max_student_num = #{course.max_student_num}, info = #{course.info} where id = #{course.id}")
    int update(@Param("course") Course course);

    /**
     * 按照课程id来查找
     * @param courseId
     * @return
     */
    @Select("select * from s_course where id = #{courseId}")
    List<Course> selectedEnable(@Param("courseId") int courseId);

    /**
     * 增加对应课程的选课人数
     * @param course_id
     * @param num
     * @return
     */
    @Update("update s_course set selected_num = selected_num + #{num} where id = #{courseId}")
    int addSelectedNum(@Param("courseId") int course_id,@Param("num") int num);

    /**
     * 减少对应课程的选课人数
     * @param course_id
     * @param num
     * @return
     */
    @Update("update s_course set selected_num = selected_num - #{num} where id = #{courseId}")
    int subtractSelectedNum(@Param("courseId") int course_id,@Param("num") int num);
}
