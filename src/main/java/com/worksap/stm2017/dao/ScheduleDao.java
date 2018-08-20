package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Schedule;
import com.worksap.stm2017.domain.StuDptRel;

import java.util.List;

public interface ScheduleDao {
    void insert(Schedule schedule);
    void delete(StuDptRel rel, String weekday);
    List<Schedule> list(String dptId, String weekday);
    String findStuDaySchedOfDpt(StuDptRel rel, String weekday);
    void updateStuDaySchedOfDpt(Schedule schedule);
    List<String> findStuSchedOfDay(String stuId, String weekday);
}
