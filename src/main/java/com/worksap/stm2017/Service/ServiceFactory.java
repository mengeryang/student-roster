package com.worksap.stm2017.Service;

public interface ServiceFactory {
    StudentService getStudentService();
    DepartmentService getDepartmentService();
    RosterService getRosterService();
    LoginService getLoginService();
    LeaveService getLeaveService();
    SettingService getSettingService();
}
