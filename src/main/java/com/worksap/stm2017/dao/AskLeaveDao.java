package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Leave;

import java.util.List;

public interface AskLeaveDao {
    void insert(Leave info);
    List<Leave> listByAskId(String askId);
    List<Leave> listByAdminStatus(int status);
    void updateAdminStatus(int id, int status);
    Leave findById(int id);
}
