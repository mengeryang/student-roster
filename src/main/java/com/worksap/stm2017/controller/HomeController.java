package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.*;
import com.worksap.stm2017.entity.DptInfo;
import com.worksap.stm2017.entity.Record;
import com.worksap.stm2017.entity.DetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
    private DepartmentService departmentService;
//    private UserService userService;
    private StudentService studentService;
    private RosterService rosterService;

    @Autowired
    public HomeController(ServiceFactory serviceFactory) {
        this.departmentService = serviceFactory.getDepartmentService();
//        this.userService = serviceFactory.getUserService();
        this.studentService = serviceFactory.getStudentService();
        this.rosterService = serviceFactory.getRosterService();
    }

    @ModelAttribute("dptlist")
    public List<DptInfo> populateDpts() {
        return departmentService.list_all_dpt();
    }

    @RequestMapping(value = {"/",""})
    public String home(Model model) {
        model.addAttribute("selInfo", new DetailInfo());
        model.addAttribute("schedList", new ArrayList<>());
        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchRoster(Model model, @ModelAttribute("selInfo") DetailInfo selInfo) {
        String raw_date = selInfo.getDate();
        List<DetailInfo> schedList;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(raw_date);
            schedList = rosterService.getDptSchedOfDay(selInfo.getDptId(), date);
            model.addAttribute("schedList", schedList);
        } catch (ParseException e){
            System.out.println("ParseFailed");
            return "home";
        }

        return "home";
    }
}
