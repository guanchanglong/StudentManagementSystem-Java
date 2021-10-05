package com.dao;

import com.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherDao {
    /**
     * 添加新的教师信息
     * @param teacher
     * @return
     */
    @Insert("insert into s_teacher values(null,#{teacher.name},#{teacher.sex},#{teacher.title},#{teacher.age},#{teacher.password})")
    int addTeacher(@Param("teacher") Teacher teacher);

    /**
     * 查找所有教师
     * @return
     */
    @Select("select * from s_teacher")
    List<Teacher> findAllTeacher();

    /**
     * 按照教师姓名查找教师信息
     * @param teacher
     * @return
     */
    @Select("select * from s_teacher where name like #{teacher.name}")
    List<Teacher> findTeacherByName(@Param("teacher") Teacher teacher);

    /**
     * 按照id删除教师信息
     * @param id
     * @return
     */
    @Delete("delete from s_teacher where id = #{id}")
    int delete(@Param("id") int id);

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    @Update("update s_teacher set name = #{teacher.name}, sex = #{teacher.sex},title = #{teacher.title},age = #{teacher.age},password = #{teacher.password} where id = #{teacher.id}")
    int update(@Param("teacher") Teacher teacher);

    /**
     * 按照姓名和密码查找教师信息
     * @param teacher
     * @return
     */
    @Select("select * from s_teacher where name = #{teacher.name} and password = #{teacher.password}")
    List<Teacher> findTeacherByNameAndPassword(@Param("teacher") Teacher teacher);

    /**
     * 修改教师的密码
     * @param id
     * @param newPassword
     * @return
     */
    @Update("update s_teacher set password = #{newPassword} where id = #{id}")
    int editPassword(@Param("id") int id,@Param("newPassword") String newPassword);
}
