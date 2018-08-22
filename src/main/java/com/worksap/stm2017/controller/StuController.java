package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.*;
import com.worksap.stm2017.domain.FreeTime;
import com.worksap.stm2017.entity.*;
import com.worksap.stm2017.util.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = {"/student"})
public class StuController {
    private RosterService rosterService;
    private StudentService studentService;
    private DepartmentService departmentService;
    private LoginService loginService;

    @Autowired
    public StuController(ServiceFactory serviceFactory) {
        this.rosterService = serviceFactory.getRosterService();
        this.studentService = serviceFactory.getStudentService();
        this.departmentService = serviceFactory.getDepartmentService();
        this.loginService = serviceFactory.getLoginService();
    }

//    @ModelAttribute("dptlist")
//    public List<DptInfo> populateDpts() {
//        return departmentService.list_all_dpt();
//    }

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
    public Map<String, String> populateWeekMap() {
        return WeekDay.WeekMap();
    }

    @ModelAttribute("weekdayList")
    public List<String> populateWeekList() {
        return WeekDay.WeekList();
    }

    @RequestMapping(value = "/home/{stuId}")
    public String studentHome(Model model, @PathVariable("stuId") String stuId) {
        List<DptInfo> dptInfos = departmentService.listDptOfStu(stuId);
        List<WorkTimeInfo> workTimeInfos =
                rosterService.getDptSchedOfDate(dptInfos.get(0).getId(), new Date());

        model.addAttribute("dpts", dptInfos);
        model.addAttribute("workTimeInfos", workTimeInfos);
        model.addAttribute("stuId", stuId);

        return "stu-home";
    }

    @RequestMapping(value = "/home/{dptId}/{date_str}", method = RequestMethod.GET)
    public String studentHomeSearch(Model model,
                                    @PathVariable("dptId") String dptId,
                                    @PathVariable("date_str") String date_str) {
        List<WorkTimeInfo> workTimeInfos;
        try {
            Date date = new SimpleDateFormat("MM-dd-yyyy").parse(date_str);
            workTimeInfos = rosterService.getDptSchedOfDate(dptId, date);
        } catch (ParseException e) {
            workTimeInfos = new ArrayList<>();
            System.out.println("date parse failed");
        }
        model.addAttribute("workTimeInfos", workTimeInfos);
        return "result :: schedule-table";
    }

    @RequestMapping(value = "/service/{stuId}/set-freetime", method = RequestMethod.GET)
    public String studentSetFreetime(Model model, @PathVariable("stuId") String stuId) {
        List<FreeTimeInfo> freeTimeInfos = studentService.listFreeTime(stuId);

        model.addAttribute("freeTimeInfos", freeTimeInfos);
        model.addAttribute("stuId", stuId);
        model.addAttribute("newFreeTimeForm", new FreeTimeForm());

        return "stu-service-set-freetime";
    }

    @RequestMapping(value = "/service/{stuId}/set-freetime/add", method = RequestMethod.POST)
    public String studentSetFreeTimeAdd(@ModelAttribute("newFreeTimeForm") FreeTimeForm newform,
                                        @PathVariable("stuId") String stuId) {
        List<String> tmp = new ArrayList<>();
        tmp.add(newform.getTimeSlot());
        studentService.addFreeTime(new FreeTimeInfo(stuId,
                newform.getWeekday(), tmp));
        return "redirect:/student/service/" + stuId + "/set-freetime";
    }

    @RequestMapping(value = "/service/{stuId}/set-freetime/delete", method = RequestMethod.POST)
    @ResponseBody
    public Message studentSetFreeTimeDelete(@RequestBody List<FreeTimeInfo> infos) {

        infos.stream().forEach(x -> studentService.deleteFreeTime(x));
        return new Message("Deletion Success");
    }

    @RequestMapping(value = "/service/{stuId}/edit-password", method = RequestMethod.GET)
    public String studentEditPasswordShow(Model model, @PathVariable("stuId") String stuId) {
        model.addAttribute("stuId", stuId);
        return "stu-service-edit-password";
    }

    @RequestMapping(value = "/service/{stuId}/edit-password", method = RequestMethod.POST)
    @ResponseBody
    public Message studentEditPasswordEdit(@RequestBody List<LoginInfo> infos) {
        LoginInfo origInfo = infos.get(0);
        LoginInfo newInfo = infos.get(1);

        if(loginService.checkLogin(origInfo)) {
            studentService.updateAccount(newInfo);
            return new Message("success");
        }
        else
            return new Message("failed");
    }
}
