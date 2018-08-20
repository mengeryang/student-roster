package com.worksap.stm2017.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory {
//    private UserService userService;
    private StudentService studentService;
    private DepartmentService departmentService;
    private RosterService rosterService;

//    public UserService getUserService() {
//        return userService;
//    }
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }


    public StudentService getStudentService() {
        return studentService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public RosterService getRosterService() {
        return rosterService;
    }

    @Autowired
    public void setRosterService(RosterService rosterService) {
        this.rosterService = rosterService;
    }
}
