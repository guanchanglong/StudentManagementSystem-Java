package com.xupt.demo.dao;

import com.xupt.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherDao extends JpaRepository<Teacher,Integer> {
    List<Teacher> findAllByNameAndPassword(String name,String password);

    @Modifying
    @Transactional
    @Query(value = "update s_teacher set password = :password where name = :name",nativeQuery = true)
    int modifyPassword(@Param("name") String name, @Param("password") String password);
}
