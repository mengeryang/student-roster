package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.RosterRecord;
import com.worksap.stm2017.domain.UserWorkTime;

import java.util.Date;
import java.util.List;

public interface RosterRecordDao {
    public void insert(RosterRecord rec);
    public List<UserWorkTime> list_dpt_rec(String dptId, Date date);
}
