package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.*;
import com.worksap.stm2017.domain.Schedule;
import com.worksap.stm2017.domain.StuDptRel;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.domain.UserWorkTime;
import com.worksap.stm2017.entity.DetailInfo;
import com.worksap.stm2017.entity.StaffInfo;
import com.worksap.stm2017.entity.WorkTimeInfo;
import com.worksap.stm2017.util.Intervals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RosterServiceImpl implements RosterService {
    private DepartmentDao departmentDao;
    private StudentDao studentDao;
    private StuDptRelDao stuDptRelDao;
    private FreeTimeDao freeTimeDao;
    private ScheduleDao scheduleDao;

    @Autowired
    public RosterServiceImpl(DaoFactory daoFactory) {
        this.departmentDao = daoFactory.getDepartmentDao();
        this.stuDptRelDao = daoFactory.getStuDptRelDao();
        this.studentDao = daoFactory.getStudentDao();
        this.freeTimeDao = daoFactory.getFreeTimeDao();
        this.scheduleDao = daoFactory.getScheduleDao();
    }

    public List<Student> getAvailableStu(WorkTimeInfo info) {
        List<String> stuIdList = stuDptRelDao.list_by_dpt(info.getDptId());
        List<Student> resList = new ArrayList<>();

        if(!Intervals.validate(info.getInterval()))
            return new ArrayList<>();

        for(String stuId: stuIdList) {
            String idle_intervals = freeTimeDao.findByDay(stuId, info.getWeekday());
            List<String> work_intervals = scheduleDao.findStuSchedOfDay(stuId, info.getWeekday());

            List<String> idle_list = Arrays.asList(idle_intervals.split(";"));
            List<String> work_list = new ArrayList<>();
            boolean available = true;

            for(String s: work_intervals) {
                List<String> tmp_list = Arrays.asList(s.split(";"));
                work_list.addAll(tmp_list);
            }

            for(String s: work_list) {
                if(Intervals.isOverlap(info.getInterval(), s)) {
                    available = false;
                    break;
                }
            }

            if(available) {
                available = false;
                for(String s: idle_list) {
                    if(Intervals.isAinB(info.getInterval(), s)) {
                        available = true;
                        break;
                    }
                }
            }

            if(available) {
                resList.add(new Student(stuId, studentDao.findName(stuId)));
            }
        }

        return resList;
    }

    public void addSchedule(WorkTimeInfo workTimeInfo) {
        String orig = scheduleDao.findStuDaySchedOfDpt(
                new StuDptRel(workTimeInfo.getStuId(), workTimeInfo.getDptId()),
                workTimeInfo.getWeekday());
        String intervals = (orig == null) ?
                workTimeInfo.getInterval() : (workTimeInfo.getInterval() + ";" + orig);

        Schedule newSchedule = new Schedule(
                workTimeInfo.getStuId(),
                workTimeInfo.getDptId(),
                workTimeInfo.getWeekday(),
                intervals
        );

        if(orig != null)
            scheduleDao.updateStuDaySchedOfDpt(newSchedule);
        else
            scheduleDao.insert(newSchedule);
    }

    public List<DetailInfo> getDptSchedOfDay(String dptId, Date date) {
        List<Schedule> scheduleList;
        List<DetailInfo> resList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String weekday;
        String date_str = df.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                weekday = "Mon"; break;
            case Calendar.TUESDAY:
                weekday = "Tue"; break;
            case Calendar.WEDNESDAY:
                weekday = "Wed"; break;
            case Calendar.THURSDAY:
                weekday = "Thu"; break;
            case Calendar.FRIDAY:
                weekday = "Fri"; break;
            case Calendar.SATURDAY:
                weekday = "Sat"; break;
            default:
                weekday = "Sun";
        }

        scheduleList = scheduleDao.list(dptId, weekday);

        if(scheduleList.isEmpty())
            return resList;

        return scheduleList.stream().map( x -> new DetailInfo(
                dptId,
                departmentDao.findName(dptId),
                x.getStuId(),
                studentDao.findName(x.getStuId()),
                date_str,
                x.getIntervals(),
                weekday
        )).collect(Collectors.toList());

    }

//    public List<UserWorkTime> getRosterBydpt(String dptId, Date date) {
//        String weekday;
//        List<UserWorkTime> res = new ArrayList<>();
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        switch (c.get(Calendar.DAY_OF_WEEK)) {
//            case Calendar.MONDAY:
//                weekday = "Mon"; break;
//            case Calendar.TUESDAY:
//                weekday = "Tue"; break;
//            case Calendar.WEDNESDAY:
//                weekday = "Wed"; break;
//            case Calendar.THURSDAY:
//                weekday = "Thu"; break;
//            case Calendar.FRIDAY:
//                weekday = "Fri"; break;
//            case Calendar.SATURDAY:
//                weekday = "Sat"; break;
//            default:
//                weekday = "Sun";
//        }
//
//        System.out.println("weekday: " + weekday);
//        if((res = rosterRecordDao.list_dpt_rec(dptId, date)).isEmpty()){
//            System.out.println("Checkin");
//            return userWorkTimeDao.listByDpt(dptId, weekday);
//        }
//        else
//            return res;
//    }
}
