package com.worksap.stm2017.Service;

import com.worksap.stm2017.entity.FreeTimeInfo;
import com.worksap.stm2017.entity.StuRegInfo;

public interface StudentService {
    void register(StuRegInfo stuRegInfo);
    void addFreeTime(FreeTimeInfo freeTimeInfo);
}
