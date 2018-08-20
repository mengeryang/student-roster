package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.FreeTime;

public interface FreeTimeDao {
    void insert(FreeTime freeTime);
    void deleteByDay(String stuId, String weekday);
    String findByDay(String stuId, String weekday);
}
