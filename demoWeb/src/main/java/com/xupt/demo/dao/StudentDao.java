package com.xupt.demo.dao;

import com.xupt.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentDao extends JpaRepository<Student,Integer> {

    List<Student> findAllByNameAndPassword(String name,String password);

    @Query(value = "select * from s_student where name = :name and classId = :classId and sex = :sex",nativeQuery = true)
    List<Student> findAllByNameAndClassIdAndSex(@Param("name") String name, @Param("classId") int classId, @Param("sex") String sex);

    @Modifying
    @Transactional
    @Query(value = "delete from s_student where name = :name and classId = :classId and sex = :sex",nativeQuery = true)
    int deleteByNameAndClassIdAndSex(@Param("name") String name,@Param("classId") int classId,@Param("sex") String sex);

    @Modifying
    @Transactional
    @Query(value = "update s_student set name = :newName,classId = :newClassId,sex = :newSex,password = :newPassword where name = :oldName and classId = :oldClassId",nativeQuery = true)
    int modifyStudent(@Param("oldName")String oldName,
                      @Param("oldClassId") int oldClassId,
                      @Param("newName") String newName,
                      @Param("newClassId") int newClassId,
                      @Param("newSex") String newSex,
                      @Param("newPassword") String newPassword);

    @Modifying
    @Transactional
    @Query(value = "update s_student set password = :password where name = :name",nativeQuery = true)
    int modifyPassword(@Param("name") String name, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "delete from s_student where classId = :classId",nativeQuery = true)
    int deleteByClassId(@Param("classId") int classId);
}
