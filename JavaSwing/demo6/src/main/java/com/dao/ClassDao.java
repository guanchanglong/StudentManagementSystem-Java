package com.dao;

import com.entity.StudentClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassDao {

    /**
     * 添加新的班级（插入班级名字和简介）
     * @param studentClass
     * @return 返回插入结果
     */
    @Insert("insert into s_class values(null,#{studentClass.name},#{studentClass.info})")
    int addClass(@Param("studentClass") StudentClass studentClass);

    /**
     * 查找所有班级
     * @return
     */
    @Select("select * from s_class")
    List<StudentClass> findAllClass();

    /**
     * 按班级名字查找班级
     * @param studentClass
     * @return
     */
    @Select("select * from s_class where name like #{studentClass.name}")
    List<StudentClass> findClassByName(@Param("studentClass") StudentClass studentClass);

    /**
     * 按班级id删除班级
     * @param id
     * @return
     */
    @Delete("delete from s_class where id = #{studentClassId}")
    int delete(@Param("studentClassId") int id);

    /**
     * 更新班级信息
     * @param studentClass 待更新的班级信息
     * @return 返回更新结果
     */
    @Update("update s_class set name = #{studentClass.name}, info = #{studentClass.info} where id = #{studentClass.id}")
    int update(@Param("studentClass") StudentClass studentClass);
}
