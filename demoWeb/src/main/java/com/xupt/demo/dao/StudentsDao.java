package com.xupt.demo.dao;

import com.xupt.demo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentsDao extends JpaRepository<Students,Integer> {

    List<Students> findAllByName(String name);

    @Modifying
    @Transactional
    @Query(value = "delete from s_students where name = :name and className = :className and sex = :sex",nativeQuery = true)
    int deleteByNameAndClassNameAndSex(@Param("name") String name,@Param("className") String className,@Param("sex") String sex);

    @Modifying
    @Transactional
    @Query(value = "update s_students set name = :newName,className = :newClassName,sex = :newSex,password = :newPassword where name = :oldName and className = :oldClassName",nativeQuery = true)
    int modifyStudents(@Param("oldName")String oldName,
                       @Param("oldClassName") String oldClassName,
                       @Param("newName") String newName,
                       @Param("newClassName") String newClassName,
                       @Param("newSex") String newSex,
                       @Param("newPassword") String newPassword);

    @Modifying
    @Transactional
    @Query(value = "update s_students set password = :password where name = :name",nativeQuery = true)
    int modifyPassword(@Param("name") String name, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "delete from s_students where className = :className",nativeQuery = true)
    int deleteByClassName(@Param("className") String className);

    @Modifying
    @Transactional
    @Query(value = "update s_students set className = :newClassName where className = :oldClassName",nativeQuery = true)
    int modifyClassName(@Param("newClassName") String newClassName,@Param("oldClassName") String oldClassName);
}
