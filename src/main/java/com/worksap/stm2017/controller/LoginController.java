package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private StudentService studentService;

    public LoginController(ServiceFactory serviceFactory) {

        this.studentService = serviceFactory.getStudentService();
    }

//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public String login(Model model) {
//        model.addAttribute("user", new User());
//        return "login";
//    }

    @RequestMapping(value = "/signup.html", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

}
