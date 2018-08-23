package com.worksap.stm2017.Service;

import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.DetailInfo;
import com.worksap.stm2017.entity.WorkTimeInfo;

import java.util.Date;
import java.util.List;

public interface RosterService {
    List<Student> getAvailableStu(WorkTimeInfo info);
    void addSchedule(WorkTimeInfo workTimeInfo);
    List<WorkTimeInfo> getDptSchedOfDate(String dptId, Date date);
    List<WorkTimeInfo> getDptSchedOfWeekday(String dptId, String weekday);
    void deleteSchedule(WorkTimeInfo workTimeInfo);
    void addRecChange(WorkTimeInfo workTimeInfo, Date date, int status);
}
