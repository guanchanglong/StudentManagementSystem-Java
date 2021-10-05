package com.dao;

import com.entity.Attendance;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AttendanceDao {

    /**
     * 插入新的考勤记录
     * @param attendance 签到表
     * @return 插入标志
     */
    @Insert("insert into s_attendance values(null,#{attendance.student_id},#{attendance.course_id},#{attendance.attendance_date})")
    int addAttendance(@Param("attendance") Attendance attendance);

    /**
     * 判断是否签到
     * @param attendance
     * @return 查看返回的Attendance是否为null，是null则没签到，不是则已签到
     */
    @Select("select * from s_attendance where student_id = #{attendance.student_id} and course_id = #{attendance.course_id} and attendance_date = #{attendance.attendance_date}")
    List<Attendance> isAttendanced(@Param("attendance") Attendance attendance);

    /**
     * 查看全部签到
     * @return
     */
    @Select("select * from s_attendance")
    List<Attendance> findAllAttendance();

    /**
     * 按照签到的学生id来查找
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where student_id = #{attendance.student_id}")
    List<Attendance> findAttendanceByStudentId(@Param("attendance") Attendance attendance);

    /**
     * 按照课程id来查找签到
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where course_id = #{attendance.course_id}")
    List<Attendance> findAttendanceByCourseId(@Param("attendance")Attendance attendance);

    /**
     * 按照签到日期来查找
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where attendance_date like #{attendance.attendance_date}")
    List<Attendance> findAttendanceByAttendanceDate(@Param("attendance")Attendance attendance);

    /**
     * 按照学生id和课程id来查找
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where student_id = #{attendance.student_id} and course_id = #{attendance.course_id}")
    List<Attendance> findAttendanceByStudentIdAndCourseId(@Param("attendance")Attendance attendance);

    /**
     * 按照学生id和签到日期来查找
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where student_id = #{attendance.student_id} and attendance_date like #{attendance.attendance_date}")
    List<Attendance> findAttendanceByStudentIdAndAttendanceDate(@Param("attendance")Attendance attendance);

    /**
     * 按照课程id和签到日期来查找
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where course_id = #{attendance.course_id} and attendance_date like #{attendance.attendance_date}")
    List<Attendance> findAttendanceByCourseIdAndAttendanceDate(@Param("attendance")Attendance attendance);

    /**
     * 按照学生id、课程id和日期来查找
     * @param attendance
     * @return
     */
    @Select("select * from s_attendance where student_id = #{attendance.student_id} and course_id = #{attendance.course_id} and attendance_date like #{attendance.attendance_date}")
    List<Attendance> findAttendanceByStudentIdAndCourseIdAndAttendanceDate(@Param("attendance")Attendance attendance);

    /**
     * 按照签到记录id来删除签到记录
     * @param id
     * @return 删除结果
     */
    @Delete("delete from s_attendance where id = #{attendanceId}")
    int delete(@Param("attendanceId") int id);

    /**
     * 查找指定课程id的签到次数
     * @param course_id 课程id
     * @return 返回结果
     */
    @Results({
            @Result(property = "key",column = "attendance_num",jdbcType = JdbcType.INTEGER),
            @Result(property = "value",column = "attendance_date",jdbcType = JdbcType.VARCHAR)
    })
    @Select("select count(id) as attendance_num,attendance_date from s_attendance where course_id = #{course_id} GROUP BY attendance_date")
    List<HashMap<String,String>> findAttendanceStatsListByCourseId(@Param("course_id") Integer course_id);

    /**
     * 查找指定课程id的在指定日期签到的次数
     * @param course_id 课程id
     * @param dateString 日期
     * @return
     */

    @Results({
            @Result(property = "key",column = "attendance_num",jdbcType = JdbcType.INTEGER),
            @Result(property = "value",column = "attendance_date",jdbcType = JdbcType.VARCHAR)
    })
    //此时的返回值为[{value=2018-05-17, key=1}, {value=2018-04-17, key=1}, {value=2018-04-18, key=1}, {value=2018-04-19, key=3}, {value=2018-04-20, key=1}, {value=2018-04-21, key=1}, {value=2018-05-03, key=1}]
    @Select("select count(id) as attendance_num,attendance_date from s_attendance where course_id = #{course_id} and attendance_date = #{dateString} GROUP BY attendance_date")
    List<HashMap<String,String>> findAttendanceStatsListByCourseIdAndDate(@Param("course_id") Integer course_id, @Param("dateString") String dateString);
}
