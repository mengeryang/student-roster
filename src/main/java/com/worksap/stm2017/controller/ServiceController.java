package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.DepartmentService;
import com.worksap.stm2017.Service.RosterService;

import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.*;
import com.worksap.stm2017.util.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
        return WeekDay.WeekMap();
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.GET)
    public String showAddStu(Model model) {
        model.addAttribute("stuRegInfo", new StuRegForm());
        return "service-add-student";
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.POST)
    public String submitStaffInfo(@ModelAttribute StuRegForm stuRegForm) {
        studentService.register(stuRegForm);
        return "service-add-student";
    }

    @RequestMapping(value = "/set-freetime", method = RequestMethod.GET)
    public String showSetFree(Model model) {
//        model.addAttribute("freeTimeInfo", new FreeTimeInfo());
        return "stu-service-set-freetime";
    }

    @RequestMapping(value = "/set-freetime/{stuId}", method = RequestMethod.GET)
    public ModelAndView searchFreeTime(Model model, @PathVariable("stuId") String stuId) {
        List<FreeTimeInfo> freeTimeInfos = studentService.listFreeTime(stuId);
        Message msg = new Message();
        model.addAttribute("freeTimeInfos", freeTimeInfos);
        if(freeTimeInfos.isEmpty())
            msg.setMsg("false");
        else
            msg.setMsg("true");

        return new ModelAndView("result :: free-time-table", "msg", msg);
    }

    @RequestMapping(value = "/set-freetime", method = RequestMethod.POST)
    public String submitFreeTime(@ModelAttribute FreeTimeInfo freeTimeInfo) {
        studentService.addFreeTime(freeTimeInfo);
        return "stu-service-set-freetime";
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
        return rosterService.getAvailableStu(workTimeInfo);
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
    public String delSearchRoster(Model model, @ModelAttribute("selInfo") WorkTimeInfo selInfo) {
        List<WorkTimeInfo> schedList;

        schedList = rosterService.getDptSchedOfWeekday(selInfo.getDptId(), selInfo.getWeekday());
        model.addAttribute("schedList", schedList);

        return "service-delete-schedule";
    }
}
