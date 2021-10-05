package com.service.impl;

import com.dao.AttendanceDao;
import com.entity.Attendance;
import com.service.AttendanceService;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AttendanceServiceImpl")
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    /**
     * 新增签到
     * @param attendance
     * @return
     */
    @Override
    public boolean addAttendance(Attendance attendance){
        int flag = attendanceDao.addAttendance(attendance);
        return flag != 0;
    }

    /**
     * 查看是否签到
     * @param attendance
     * @return
     */
    @Override
    public boolean isAttendanced(Attendance attendance){
        if (attendanceDao.isAttendanced(attendance)!=null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 从Student_id、Course_id和Attendance_date查看签到人数
     * @param attendance
     * @return 签到人数
     */
    @Override
    public List<Attendance> getAttendancedList(Attendance attendance){
        //如果外界没有输入的话
        if (attendance.getStudent_id()==0 && attendance.getCourse_id()==0 && StringUtil.isEmpty(attendance.getAttendance_date())){
            return attendanceDao.findAllAttendance();
        }

        //如果只有Student_id不为空的话
        if (attendance.getStudent_id()!=0 && attendance.getCourse_id()==0 && StringUtil.isEmpty(attendance.getAttendance_date())){
            return attendanceDao.findAttendanceByStudentId(attendance);
        }

        //如果Student_id和Course_id不为空，而Attendance_date为空的话
        if (attendance.getStudent_id()!=0 && attendance.getCourse_id()!=0 && StringUtil.isEmpty(attendance.getAttendance_date())){
            return attendanceDao.findAttendanceByStudentIdAndCourseId(attendance);
        }

        //如果Student_id和Attendance_date不为空，而Course_id为空的话
        if (attendance.getStudent_id()!=0 && attendance.getCourse_id()==0 && !StringUtil.isEmpty(attendance.getAttendance_date())){
            String date = attendance.getAttendance_date();
            date = "%"+date+"%";
            attendance.setAttendance_date(date);
            return attendanceDao.findAttendanceByStudentIdAndAttendanceDate(attendance);
        }

        //如果只有Course_id不为空的话
        if (attendance.getStudent_id()==0 && attendance.getCourse_id()!=0 && StringUtil.isEmpty(attendance.getAttendance_date())){
            return attendanceDao.findAttendanceByCourseId(attendance);
        }

        //如果Course_id和Attendance_date不为空，而Student_id为空的话
        if (attendance.getStudent_id()==0 && attendance.getCourse_id()!=0 && !StringUtil.isEmpty(attendance.getAttendance_date())){
            String date = attendance.getAttendance_date();
            date = "%"+date+"%";
            attendance.setAttendance_date(date);
            return attendanceDao.findAttendanceByCourseIdAndAttendanceDate(attendance);
        }

        //如果只有Attendance_date不为空的话
        if (attendance.getStudent_id()==0 && attendance.getCourse_id()==0 && !StringUtil.isEmpty(attendance.getAttendance_date())){
            String date = attendance.getAttendance_date();
            date = "%"+date+"%";
            attendance.setAttendance_date(date);
            return attendanceDao.findAttendanceByAttendanceDate(attendance);
        }

        //如果Student_id、Course_id和Attendance_date都不为空的话
        if (attendance.getStudent_id()!=0 && attendance.getCourse_id()!=0 && !StringUtil.isEmpty(attendance.getAttendance_date())){
            String date = attendance.getAttendance_date();
            date = "%"+date+"%";
            attendance.setAttendance_date(date);
            return attendanceDao.findAttendanceByStudentIdAndCourseIdAndAttendanceDate(attendance);
        }

        return null;
    }

    /**
     * 根据签到记录id删除签到
     * @param id
     * @return 返回删除结果
     */
    @Override
    public boolean delete(int id){
        int flag = attendanceDao.delete(id);
        return flag!=0;
    }

    /**
     * 查找指定课程id的在指定日期签到的次数
     * @param course_id
     * @param dateString
     * @return
     */
    @Override
    public List<HashMap<String, String>> getAttendanceStatsList(Integer course_id, String dateString){
        List<HashMap<String,String>> result = new ArrayList<>();
        //日期为空时
        if(StringUtil.isEmpty(dateString)){
            for(HashMap<String,String> maps:attendanceDao.findAttendanceStatsListByCourseId(course_id)){
                HashMap<String,String> map = new HashMap<>();
                String value = "";
                String str = maps.toString();
                String key = str.substring(str.indexOf("key=")+4,str.length()-1);
                value = str.substring(str.indexOf("value=")+6,str.indexOf(","));
                if (!value.equals("")){
                    map.put(key,value);
                }
                result.add(map);
            }
        }else{
            for(HashMap<String,String> maps:attendanceDao.findAttendanceStatsListByCourseIdAndDate(course_id,dateString)){
                HashMap<String,String> map = new HashMap<>();
                String value = "";
                String str = maps.toString();
                String key = str.substring(str.indexOf("key=")+4,str.length()-1);
                value = str.substring(str.indexOf("value=")+6,str.indexOf(","));
                if (!value.equals("")){
                    map.put(key,value);
                }
                result.add(map);
            }
        }
        return result;
    }
}
