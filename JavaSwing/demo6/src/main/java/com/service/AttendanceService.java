package com.service;

import com.entity.Attendance;

import java.util.HashMap;
import java.util.List;

public interface AttendanceService {
    boolean addAttendance(Attendance attendance);

    boolean isAttendanced(Attendance attendance);

    List<Attendance> getAttendancedList(Attendance attendance);

    boolean delete(int id);

    List<HashMap<String, String>> getAttendanceStatsList(Integer course_id, String dateString);
}
