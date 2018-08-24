package com.worksap.stm2017.dao;

public interface SettingDao {
    void setSetFree(boolean flag);
    void setWorkload(int workload);
    boolean getSetFree();
    int getWorkload();

}
