package com.worksap.stm2017.Service;

public interface SettingService {
    boolean getSetFree();
    int getWorkload();
    void setSetFree(boolean flag);
    void setWorkload(int workload);
}
