package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.*;
import com.worksap.stm2017.entity.DptInfo;
import com.worksap.stm2017.entity.DetailInfo;
import com.worksap.stm2017.entity.StuInfo;
import com.worksap.stm2017.entity.WorkTimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
    private DepartmentService departmentService;
    private StudentService studentService;
    private RosterService rosterService;

    @Autowired
    public HomeController(ServiceFactory serviceFactory) {
        this.departmentService = serviceFactory.getDepartmentService();
        this.studentService = serviceFactory.getStudentService();
        this.rosterService = serviceFactory.getRosterService();
    }

    @ModelAttribute("dptlist")
    public List<DptInfo> populateDpts() {
        return departmentService.list_all_dpt();
    }

    @ModelAttribute("stulist")
    public List<StuInfo> populateStus() {
        return studentService.list_all_stu();
    }
    @ModelAttribute("dptmap")
    public Map<String, String> populateDptMap() {
        Map<String, String> res = new HashMap<>();
        for(DptInfo info: departmentService.list_all_dpt())
            res.put(info.getId(), info.getName());
        return res;
    }

    @ModelAttribute("stumap")
    public Map<String, String> populateStuMap() {
        Map<String, String> res = new HashMap<>();
        for(StuInfo info: studentService.list_all_stu())
            res.put(info.getId(), info.getName());
        return res;
    }


    @ModelAttribute("weekdayMap")
    public Map<String, String> populateWeek() {
        Map<String, String> res = new HashMap<>();
        res.put("1", "Mon");
        res.put("2", "Tue");
        res.put("3", "Wed");
        res.put("4", "Thu");
        res.put("5", "Fri");
        res.put("6", "Sat");
        res.put("0", "Sun");
        return res;
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
//        List<DetailInfo> schedList;
        List<WorkTimeInfo> schedList;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(raw_date);
            schedList = rosterService.getDptSchedOfDate(selInfo.getDptId(), date);
            model.addAttribute("schedList", schedList);
        } catch (ParseException e){
            System.out.println("ParseFailed");
            return "home";
        }

        return "home";
    }
}
