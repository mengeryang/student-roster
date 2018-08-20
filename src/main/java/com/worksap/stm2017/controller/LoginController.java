package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import com.worksap.stm2017.Service.UserService;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
//    private ServiceFactory serviceFactory;
//    private UserService userService;
    private StudentService studentService;

    public LoginController(ServiceFactory serviceFactory) {
//        this.serviceFactory = serviceFactory;
//        userService = serviceFactory.getUserService();
        this.studentService = serviceFactory.getStudentService();
    }

//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String loginCheck(@ModelAttribute("user") User user) {
//        if(userService.login(user))
//            return "home";
//        else
//            return "login";
//    }

    @RequestMapping(value = "/signup.html", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

//    @RequestMapping(value = "/loginCheck.html", method = RequestMethod.POST)
//    public ModelAndView loginCheck(@RequestBody User user) {
//        if(userService.login(user))
//            return new ModelAndView("home");
//        else
//            return new ModelAndView("login");
//    }

//    @RequestMapping(value = "/signupinfo.html", method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void regInfo(@RequestBody User user) {
//        userService.signup(user);
//    }
}
