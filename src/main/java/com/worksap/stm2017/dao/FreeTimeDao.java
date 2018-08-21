package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.FreeTime;

import java.util.List;

public interface FreeTimeDao {
    void insert(FreeTime freeTime);
    void deleteByDay(String stuId, String weekday);
    List<String> findByDay(String stuId, String weekday);
}
