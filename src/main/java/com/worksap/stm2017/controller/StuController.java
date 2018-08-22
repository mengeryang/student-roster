package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.DepartmentService;
import com.worksap.stm2017.Service.RosterService;
import com.worksap.stm2017.Service.ServiceFactory;
import com.worksap.stm2017.Service.StudentService;
import com.worksap.stm2017.entity.DptInfo;
import com.worksap.stm2017.entity.StuInfo;
import com.worksap.stm2017.entity.WorkTimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    public StuController(ServiceFactory serviceFactory) {
        this.rosterService = serviceFactory.getRosterService();
        this.studentService = serviceFactory.getStudentService();
        this.departmentService = serviceFactory.getDepartmentService();
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

    @RequestMapping(value = "/home/{stuId}")
    public String studentHome(Model model, @PathVariable("stuId") String stuId) {
        List<DptInfo> dptInfos = departmentService.listDptOfStu(stuId);
        List<WorkTimeInfo> workTimeInfos =
                rosterService.getDptSchedOfDate(dptInfos.get(0).getId(), new Date());

        model.addAttribute("dpts", dptInfos);
        model.addAttribute("workTimeInfos", workTimeInfos);

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
        return "result : schedule-table";
    }
}
