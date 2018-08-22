package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.DepartmentService;
import com.worksap.stm2017.Service.LoginService;
import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import com.worksap.stm2017.entity.LoginInfo;
import com.worksap.stm2017.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private StudentService studentService;
    private DepartmentService departmentService;
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
        model.addAttribute("msg", new Message(""));
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute("loginInfo") LoginInfo loginInfo, Model model) {
        if(!loginService.checkLogin(loginInfo)) {
            model.addAttribute("msg", new Message("wrong id or password"));
            return "login";
        }

        if(loginService.isAdmin(loginInfo))
            return "redirect:/admin/home";
        else
            return "redirect:/student/home/" + loginInfo.getId();
    }

}
