package com.worksap.stm2017.dao;

public interface DaoFactory {
    DepartmentDao getDepartmentDao();
    StuDptRelDao getStuDptRelDao();
    StudentDao getStudentDao();
    FreeTimeDao getFreeTimeDao();
    ScheduleDao getScheduleDao();
    RecChangeDao getRecChangeDao();
}
