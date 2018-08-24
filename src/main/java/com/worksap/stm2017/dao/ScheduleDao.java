package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Schedule;
import com.worksap.stm2017.domain.StuDptRel;

import java.util.List;

public interface ScheduleDao {
    void insert(Schedule schedule);
    void delete(Schedule schedule);
    List<String> findStuDaySchedOfDpt(String stuId, String dptId, String weekday);
    List<String> findStuSchedOfDay(String stuId, String weekday);
    void deleteBySlotDay(String stuId, String slot, String weekday);
}
