package com.xupt.demo.dao;

import com.xupt.demo.entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ClassDao extends JpaRepository<StudentClass,Integer> {
    StudentClass findAllById(int classId);

    @Query(value = "select name from s_class",nativeQuery = true)
    List<String> findAllReturnName();

    @Query(value = "select id from s_class where name = :name",nativeQuery = true)
    int findClassByClassName(@Param("name") String name);

    List<StudentClass> findAllByName(String name);

    @Modifying
    @Transactional
    @Query(value = "delete from s_class where id = :id",nativeQuery = true)
    int deleteClassById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "update s_class set name = :name, info = :info where id = :id",nativeQuery = true)
    int modifyClassById(@Param("id") int id,@Param("name") String name,@Param("info") String info);

    @Query(value = "select id from s_class",nativeQuery = true)
    List<Integer> findAllClassId();

}
