package com.worksap.stm2017.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryJdbcImpl implements DaoFactory {
//    private UserDao userDao;
//    private UserWorkTimeDao userWorkTimeDao;
    private DepartmentDao departmentDao;
//    private RosterRecordDao rosterRecordDao;
    private StuDptRelDao stuDptRelDao;
    private StudentDao studentDao;
    private FreeTimeDao freeTimeDao;
    private ScheduleDao scheduleDao;
    private RecChangeDao recChangeDao;

//    public UserDao getUserDao() {
//        return userDao;
//    }
//
//    @Autowired
//    public void setUserDao(@Qualifier("userDaoImpl") UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    public UserWorkTimeDao getUserWorkTimeDao() {
//        return userWorkTimeDao;
//    }
//
//    @Autowired
//    public void setUserWorkTimeDao(UserWorkTimeDao userWorkTimeDao) {
//        this.userWorkTimeDao = userWorkTimeDao;
//    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

//    public RosterRecordDao getRosterRecordDao() {
//        return rosterRecordDao;
//    }
//
//    @Autowired
//    public void setRosterRecordDao(RosterRecordDao rosterRecordDao) {
//        this.rosterRecordDao = rosterRecordDao;
//    }

    public StuDptRelDao getStuDptRelDao() {
        return stuDptRelDao;
    }

    @Autowired
    public void setStuDptRelDao(StuDptRelDao stuDptRelDao) {
        this.stuDptRelDao = stuDptRelDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public FreeTimeDao getFreeTimeDao() {
        return freeTimeDao;
    }

    @Autowired
    public void setFreeTimeDao(FreeTimeDao freeTimeDao) {
        this.freeTimeDao = freeTimeDao;
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    @Autowired
    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public RecChangeDao getRecChangeDao() {
        return recChangeDao;
    }

    @Autowired
    public void setRecChangeDao(RecChangeDao recChangeDao) {
        this.recChangeDao = recChangeDao;
    }
}
