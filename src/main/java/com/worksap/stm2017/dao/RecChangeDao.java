package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Schedule;

import java.util.Date;
import java.util.List;

public interface RecChangeDao {
    void insert(Schedule schedule, Date date);
    void delete(String dptId, Date date);
    List<Schedule> find(String dptId, Date date);
}
