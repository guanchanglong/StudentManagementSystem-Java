package com.dao;

import com.entity.Course;
import com.entity.SelectedCourse;
import com.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelectedCourseDao {
    /**
     * 学生增加新的选课
     * @param selectedCourse
     * @return
     */
    @Insert("insert into s_selected_course values(null,#{selectedCourse.student_id},#{selectedCourse.course_id})")
    int addSelectedCourse(@Param("selectedCourse") SelectedCourse selectedCourse);

    /**
     * 更新选课信息
     * @param selectedCourse
     * @return
     */
    @Update("update s_selected_course set student_id = #{selectedCourse.student_id},course_id = #{selectedCourse.course_id} where id = #{selectedCourse.id}")
    int updateSelectedCourse(@Param("selectedCourse") SelectedCourse selectedCourse);

    /**
     * 查找所有的选课信息
     * @return
     */
    @Select("select * from s_selected_course")
    List<SelectedCourse> findAllSelectedCourse();

    /**
     * 按学生id查找所有的选课信息
     * @param selectedCourse
     * @return
     */
    @Select("select * from s_selected_course where student_id = #{selectedCourse.student_id}")
    List<SelectedCourse> findSelectCourseByStudentId(@Param("selectedCourse") SelectedCourse selectedCourse);

    /**
     * 按照课程id查找选课信息
     * @param selectedCourse
     * @return
     */
    @Select("select * from s_selected_course where course_id = #{selectCourse.course_id}")
    List<SelectedCourse> findSelectCourseByCourseId(@Param("selectCourse") SelectedCourse selectedCourse);

    /**
     * 按照学生id和课程id查找选课信息
     * @param selectedCourse
     * @return
     */
    @Select("select * from s_selected_course where student_id = #{selectedCourse.student_id} and course_id = #{selectedCourse.course_id}")
    List<SelectedCourse> findSelectCourseByStudentIdAndCourseId(@Param("selectedCourse") SelectedCourse selectedCourse);

    /**
     * 按照id来删除选课
     * @param id
     * @return
     */
    @Delete("delete from s_selected_course where id = #{id}")
    int delete(@Param("id") int id);

    /**
     * 查找选择了该课程的学生的信息
     * @param course
     * @return
     */
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "classId",column = "classId"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex")
    })
    @Select("select s.id, s.classId, s.name, s.sex from s_selected_course sc, s_student s where sc.student_id = s.id and course_id = #{course.id}")
    List<Student> getSelectedCourseStudentList(@Param("course") Course course);
}
