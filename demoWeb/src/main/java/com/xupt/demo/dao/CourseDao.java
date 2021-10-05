package com.xupt.demo.dao;

import com.xupt.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDao extends JpaRepository<Course,Integer> {

    @Query(value = "select name from s_course",nativeQuery = true)
    List<String> findAllReturnName();

    @Query(value = "select id from s_course where name = :name",nativeQuery = true)
    List<Integer> findCourseIdByName(@Param("name") String name);
}
