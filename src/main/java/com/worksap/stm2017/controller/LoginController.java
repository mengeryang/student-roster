package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.LoginService;
import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import com.worksap.stm2017.entity.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private StudentService studentService;
    private LoginService loginService;

    @Autowired
    public LoginController(ServiceFactory serviceFactory) {

        this.studentService = serviceFactory.getStudentService();
        this.loginService = serviceFactory.getLoginService();
    }

//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public String login(Model model) {
//        model.addAttribute("user", new User());
//        return "login";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginInfo", new LoginInfo());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute("loginInfo") LoginInfo loginInfo) {
        if(!loginService.checkLogin(loginInfo)) {
            return "/";
        }

        if(loginService.isAdmin(loginInfo))
            return "redirect: /home/";
        else
            return "redirect: /student/home/" + loginInfo.getId();
    }

}
