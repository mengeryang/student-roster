package com.worksap.stm2017.Service;

import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.FreeTimeInfo;
import com.worksap.stm2017.entity.StuInfo;
import com.worksap.stm2017.entity.StuRegInfo;

import java.util.List;

public interface StudentService {
    void register(StuRegInfo stuRegInfo);
    void addFreeTime(FreeTimeInfo freeTimeInfo);
    List<StuInfo> list_all_stu();
    List<FreeTimeInfo> listFreeTime(String stuId);
}
