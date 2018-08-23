package com.worksap.stm2017.controller;

import com.worksap.stm2017.Service.*;
import com.worksap.stm2017.domain.Leave;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.*;
import com.worksap.stm2017.util.Admin;
import com.worksap.stm2017.util.ScheduleStatus;
import com.worksap.stm2017.util.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private DepartmentService departmentService;
    private StudentService studentService;
    private RosterService rosterService;
    private LeaveService leaveService;

    @Autowired
    public AdminController(ServiceFactory serviceFactory) {
        this.departmentService = serviceFactory.getDepartmentService();
        this.studentService = serviceFactory.getStudentService();
        this.rosterService = serviceFactory.getRosterService();
        this.leaveService = serviceFactory.getLeaveService();
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

    @ModelAttribute("weekdayList")
    public List<String> populateWeekList() {
        return WeekDay.WeekList();
    }

    @RequestMapping(value = {"/home","/home/"})
    public String home(Model model) {
        List<WorkTimeInfo> workTimeInfos = new ArrayList<>();
        List<DptInfo> dptInfos = departmentService.list_all_dpt();
        List<LeaveInfo> leaveInfos = leaveService.listLeaveMsgForAdmin();

        for(DptInfo dpt: dptInfos) {
            List<WorkTimeInfo> tmpInfos = rosterService.getDptSchedOfDate(dpt.getId(), new Date());
            workTimeInfos.addAll(tmpInfos);
        }
//        model.addAttribute("selInfo", new DetailInfo());
//        model.addAttribute("schedList", new ArrayList<>());

        model.addAttribute("workTimeInfos", workTimeInfos);
        model.addAttribute("msgs", leaveInfos);
        return "admin-home";
    }

    @RequestMapping(value = "/home/reply/{msgId}/{reply}", method = RequestMethod.GET)
    @ResponseBody
    public Message reply(@PathVariable("msgId") String msgId, @PathVariable("reply") String reply) {
        Leave leave;
        WorkTimeInfo workTimeInfo;
        List<String> tmp = new ArrayList<>();
        if(reply.equals("accept")){
            leaveService.updateAdminStatus(Integer.parseInt(msgId), Admin.ACCEPT);
            leave = leaveService.findById(Integer.parseInt(msgId));
            tmp.add(leave.getTimeSlot());
            rosterService.addRecChange(new WorkTimeInfo(leave.getAskId(), leave.getDptId(), "", tmp,""),
                    leave.getDate(), ScheduleStatus.DELETED);
        }
        else {
            leaveService.updateAdminStatus(Integer.parseInt(msgId), Admin.DENY);
        }
        return new Message();
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

    @RequestMapping(value = "/service/add-student", method = RequestMethod.GET)
    public String showAddStu(Model model) {
        model.addAttribute("stuRegForm", new StuRegForm());
        return "admin-service-add-student";
    }

    @RequestMapping(value = "/service/add-student", method = RequestMethod.POST)
    public String addStu(@ModelAttribute StuRegForm stuRegForm) {
        studentService.register(stuRegForm);
        return "admin-service-add-student";
    }

    @RequestMapping(value = "/service/edit-schedule", method = RequestMethod.GET)
    public String showEditSchedule() {
        return "admin-service-edit-schedule";
    }

    @RequestMapping(value = "/service/edit-schedule/{dptId}/{weekday}", method = RequestMethod.GET)
    public String editScheduleSearch(Model model,
                                     @PathVariable("dptId") String dptId,
                                     @PathVariable("weekday") String weekday) {
        List<WorkTimeInfo> workTimeInfos;
        workTimeInfos = rosterService.getDptSchedOfWeekday(dptId, weekday);
        model.addAttribute("workTimeInfos", workTimeInfos);
        return "result :: schedule-editable";
    }

    @RequestMapping(value = "/service/edit-schedule/filter", method = RequestMethod.POST)
    @ResponseBody
    public List<StuInfo> editScheduleFilter(@RequestBody WorkTimeInfo workTimeInfo) {
        return rosterService.getAvailableStu(workTimeInfo).stream()
                .map(x -> new StuInfo(x.getId(), x.getName())).collect(Collectors.toList());
    }

    @RequestMapping(value = "/service/edit-schedule/add", method = RequestMethod.POST)
    @ResponseBody
    public Message editScheduleAdd(@RequestBody WorkTimeInfo workTimeInfo) {
        rosterService.addSchedule(workTimeInfo);
        return new Message();
    }

    @RequestMapping(value = "/service/edit-schedule/delete", method = RequestMethod.POST)
    @ResponseBody
    public Message editScheduleDelete(@RequestBody List<WorkTimeInfo> workTimeInfos) {
        workTimeInfos.stream().forEach(x -> rosterService.deleteSchedule(x));
        return new Message();
    }

//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public String searchRoster(Model model, @ModelAttribute("selInfo") DetailInfo selInfo) {
//        String raw_date = selInfo.getDate();
////        List<DetailInfo> schedList;
//        List<WorkTimeInfo> schedList;
//        try {
//            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(raw_date);
//            schedList = rosterService.getDptSchedOfDate(selInfo.getDptId(), date);
//            model.addAttribute("schedList", schedList);
//        } catch (ParseException e){
//            System.out.println("ParseFailed");
//            return "home";
//        }
//
//        return "home";
//    }

}
