package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.RosterService;
import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/student"})
public class StuController {
    private RosterService rosterService;
    private StudentService studentService;

    @Autowired
    public StuController(ServiceFactory serviceFactory) {
        this.rosterService = serviceFactory.getRosterService();
        this.studentService = serviceFactory.getStudentService();
    }


}
