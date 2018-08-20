package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.UserWorkTime;

import java.util.List;

public interface UserWorkTimeDao {
    void insert(UserWorkTime workTime);
    void delete(UserWorkTime workTime);
    List<UserWorkTime> listByUser(String userid, String weekday);
    List<UserWorkTime> listByDpt(String dptid, String weekday);
}
