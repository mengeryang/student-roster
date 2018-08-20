package com.worksap.stm2017.dao;

public interface DaoFactory {
//    UserDao getUserDao();
//    UserWorkTimeDao getUserWorkTimeDao();
    DepartmentDao getDepartmentDao();
//    RosterRecordDao getRosterRecordDao();
    StuDptRelDao getStuDptRelDao();
    StudentDao getStudentDao();
    FreeTimeDao getFreeTimeDao();
    ScheduleDao getScheduleDao();
    RecChangeDao getRecChangeDao();
}
