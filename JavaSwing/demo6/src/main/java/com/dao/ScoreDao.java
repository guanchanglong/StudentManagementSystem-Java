package com.dao;

import com.entity.ResultScore;
import com.entity.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreDao {

    /**
     * 新增一条成绩信息
     * @param score
     * @return
     */
    @Insert("insert into s_score values(null,#{score.student_id},#{score.course_id},#{score.score})")
    int addScore(@Param("score") Score score);

    /**
     * 查找所有的成绩信息
     * @return
     */
    @Select("select * from s_score")
    List<Score> findAllScore();

    /**
     * 按照学生id查找成绩信息
     * @param score
     * @return
     */
    @Select("select * from s_score where student_id = #{score.student_id}")
    List<Score> findScoreByStudentId(@Param("score")Score score);

    /**
     * 按照课程id来查找成绩信息
     * @param score
     * @return
     */
    @Select("select * from s_score where course_id = #{score.course_id}")
    List<Score> findScoreByCourseId(@Param("score")Score score);

    /**
     * 按照学生id和课程id来查找成绩信息
     * @param score
     * @return
     */
    @Select("select * from s_score where student_id = #{score.student_id} and course_id = #{score.course_id}")
    List<Score> findScoreByStudentIdAndCourseId(@Param("score")Score score);

    /**
     * 查找是否有这条成绩记录
     * @param score
     * @return
     */
    @Select("select * from s_score where student_id = #{score.student_id} and course_id = #{score.course_id}")
    List<Score> isAdd(@Param("score") Score score);

    /**
     * 更新成绩信息
     * @param id
     * @param score
     * @return
     */
    @Update("update s_score set score = #{score} where id = #{scoreId}")
    int update(@Param("scoreId") int id,@Param("score") int score);

    /**
     * 按照id删除成绩信息
     * @param id
     * @return
     */
    @Delete("delete from s_score where id = #{scoreId}")
    int delete(@Param("scoreId") int id);

    /**
     * 返回对应课程的一些成绩信息
     * @param courseId
     * @return
     */
    @Results({
            @Result(property = "student_num",column = "student_num"),
            @Result(property = "max_score",column = "max_score"),
            @Result(property = "min_score",column = "min_score"),
            @Result(property = "mid_score",column = "mid_score")
    })
    //student_num指该课程有多少个学生的成绩，max_score指该课程中的最高成绩，min_score指该课程中的最低成绩，mid_score指该课程学生成绩的平均分
    @Select("select count(id) as student_num,max(score) as max_score,min(score) as min_score,AVG(score) as mid_score from s_score where course_id = #{courseId}")
    List<ResultScore> getStatsInfo(@Param("courseId") int courseId);
}
