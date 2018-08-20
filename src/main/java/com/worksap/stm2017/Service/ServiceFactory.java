package com.worksap.stm2017.Service;

public interface ServiceFactory {
//    UserService getUserService();
    StudentService getStudentService();
    DepartmentService getDepartmentService();
    RosterService getRosterService();
}
