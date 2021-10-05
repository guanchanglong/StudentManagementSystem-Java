package com.xupt.demo.dao;

import com.xupt.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreDao extends JpaRepository<Score,Integer> {
    @Query(value = "select score from s_score where course_id = :courseId",nativeQuery = true)
    List<Integer> findScoreByCourseId(@Param("courseId") int courseId);
}
