package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.DepartmentService;
import com.worksap.stm2017.Service.RosterService;

import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/service")
public class ServiceController {
    private RosterService rosterService;
    private StudentService studentService;
    private DepartmentService departmentService;

    @Autowired
    public ServiceController(ServiceFactory serviceFactory) {
        this.rosterService = serviceFactory.getRosterService();
        this.studentService = serviceFactory.getStudentService();
        this.departmentService = serviceFactory.getDepartmentService();
    }

    @ModelAttribute("dptlist")
    public List<DptInfo> populateDpts() {
        return departmentService.list_all_dpt();
    }

    @ModelAttribute("weekdayList")
    public List<String> populateWeek() {
        return Arrays.asList("Sun","Mon","Tue","Wed","Thu","Fri","Sat");
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.GET)
    public String showAddStu(Model model) {
        model.addAttribute("stuRegInfo", new StuRegInfo());
        return "service-add-student";
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.POST)
    public String submitStaffInfo(@ModelAttribute StuRegInfo stuRegInfo) {
        studentService.register(stuRegInfo);
        return "service-add-student";
    }

    @RequestMapping(value = "/set-freetime", method = RequestMethod.GET)
    public String showSetFree(Model model) {
        model.addAttribute("freeTimeInfo", new FreeTimeInfo());
        return "service-set-freetime";
    }

    @RequestMapping(value = "/set-freetime", method = RequestMethod.POST)
    public String submitFreeTime(@ModelAttribute FreeTimeInfo freeTimeInfo) {
        studentService.setFreeTime(freeTimeInfo);
        return "service-set-freetime";
    }

    @RequestMapping(value = "/add-schedule", method = RequestMethod.GET)
    public String showAddSchd(Model model) {
        model.addAttribute("workTimeInfo", new WorkTimeInfo());
        return "service-add-schedule";
    }

    @RequestMapping(value = "/add-schedule", method = RequestMethod.POST)
    public String addSchedule(@ModelAttribute WorkTimeInfo workTimeInfo) {
        rosterService.addSchedule(workTimeInfo);
        return "service-add-schedule";
    }

    @RequestMapping(value = "/add-schedule/filter", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> filterStudent(@RequestBody WorkTimeInfo workTimeInfo) {
        List<Student> studentList = rosterService.getAvailableStu(workTimeInfo);
        return studentList;
    }

    @RequestMapping(value = "/delete-schedule", method = RequestMethod.GET)
    public String showDelSchd(Model model) {
        model.addAttribute("schedList", new ArrayList<>());
        model.addAttribute("selInfo", new DetailInfo());
        return "service-delete-schedule";
    }

    @RequestMapping(value = "/delete-schedule", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteSched(@RequestBody List<WorkTimeInfo> infoList) {
        infoList.stream().forEach( x -> rosterService.deleteSchedule(x));
        return new Message(" deletion success!");
    }

    @RequestMapping(value = "/delete-schedule/search", method = RequestMethod.POST)
    public String delSearchRoster(Model model, @ModelAttribute("selInfo") DetailInfo selInfo) {
        List<DetailInfo> schedList;

        schedList = rosterService.getDptSchedOfWeekday(selInfo.getDptId(), selInfo.getWeekday());
        model.addAttribute("schedList", schedList);

        return "service-delete-schedule";
    }
}
