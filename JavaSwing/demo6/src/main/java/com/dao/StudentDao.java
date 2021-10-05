package com.dao;

import com.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao {

    /**
     * 添加新的学生信息
     * @param student
     * @return
     */
    @Insert("insert into s_student values(null,#{student.name},#{student.classId},#{student.password},#{student.sex})")
    int addStudent(@Param("student") Student student);

    /**
     * 查找所有学生
     * @return
     */
    @Select("select * from s_student")
    List<Student> findAllStudent();

    /**
     * 按照学生姓名查找学生
     * @param student
     * @return
     */
    @Select("select * from s_student where name like #{student.name}")
    List<Student> findStudentByName(@Param("student") Student student);

    /**
     * 按照班级id来查找学生
     * @param student
     * @return
     */
    @Select("select * from s_student where classId = #{student.classId}")
    List<Student> findStudentByClassId(@Param("student") Student student);

    /**
     * 按照学生姓名和班级id来查找学生
     * @param student
     * @return
     */
    @Select("select * from s_student where name like #{student.name} and classId = #{student.classId}")
    List<Student> findStudentByNameAndClassId(@Param("student") Student student);

    /**
     * 按照id删除学生信息
     * @param id
     * @return
     */
    @Delete("delete from s_student where id = #{id}")
    int delete(@Param("id") int id);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @Update("update s_student set name = #{student.name}, classId = #{student.classId},sex = #{student.sex},password = #{student.password} where id = #{student.id}")
    int update(@Param("student") Student student);

    /**
     * 按照学生id和password查找学生信息
     * @param student
     * @return
     */
    @Select("select * from s_student where id = #{student.id} and password = #{student.password}")
    List<Student> findStudentByIdAndPassword(@Param("student") Student student);

    /**
     * 修改相应学生id的密码
     * @param id
     * @param newPassword
     * @return
     */
    @Update("update s_student set password = #{newPassword} where id = #{id}")
    int updatePassword(@Param("id") int id,@Param("newPassword") String newPassword);

    /**
     * 按照学生姓名和学生密码查找学生
     * @param student
     * @return
     */
    @Select("select * from s_student where name = #{student.name} and password = #{student.password}")
    List<Student> findStudentByNameAndPassword(@Param("student") Student student);

    /**
     * 设置不使用外键
     * @return
     */
    @Update("set foreign_key_checks = 0")
    int setUnUsingForeignKey();

    /**
     * 设置使用外键
     * @return
     */
    @Update("set foreign_key_checks = 1")
    int setUsingForeignKey();
}
