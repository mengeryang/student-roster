package com.worksap.stm2017.Service;

import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.domain.UserWorkTime;
import com.worksap.stm2017.entity.DetailInfo;
import com.worksap.stm2017.entity.StaffInfo;
import com.worksap.stm2017.entity.WorkTimeInfo;

import java.util.Date;
import java.util.List;

public interface RosterService {
    List<Student> getAvailableStu(WorkTimeInfo info);
    void addSchedule(WorkTimeInfo workTimeInfo);
    List<DetailInfo> getDptSchedOfDay(String dptId, Date date);
}
