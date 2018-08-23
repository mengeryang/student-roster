package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Schedule;

import java.util.Date;
import java.util.List;

public interface RecChangeDao {
    void insert(Schedule schedule, Date date, int status);
    void delete(Schedule schedule, Date date);
    List<String> findRecByStuDptDate(String stuId, String dptId, Date date);
    boolean isDelete(Schedule schedule, Date date);
//    List<Schedule> find(String dptId, Date date);
}
