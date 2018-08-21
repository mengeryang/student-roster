package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.*;
import com.worksap.stm2017.domain.Schedule;
import com.worksap.stm2017.domain.StuDptRel;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.DetailInfo;
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
    private RecChangeDao recChangeDao;

    @Autowired
    public RosterServiceImpl(DaoFactory daoFactory) {
        this.departmentDao = daoFactory.getDepartmentDao();
        this.stuDptRelDao = daoFactory.getStuDptRelDao();
        this.studentDao = daoFactory.getStudentDao();
        this.freeTimeDao = daoFactory.getFreeTimeDao();
        this.scheduleDao = daoFactory.getScheduleDao();
        this.recChangeDao = daoFactory.getRecChangeDao();
    }

    public List<Student> getAvailableStu(WorkTimeInfo info) {
        List<String> stuIdList = stuDptRelDao.list_by_dpt(info.getDptId());
        List<Student> resList = new ArrayList<>();
        String weekday = info.getWeekday();
        String target_slot = info.getInterval();

        if(!Intervals.validate(info.getInterval()))
            return new ArrayList<>();

        for(String stuId: stuIdList) {
            List<String> idle_slots = freeTimeDao.findByDay(stuId, weekday);
            List<String> work_slots = scheduleDao.findStuSchedOfDay(stuId, info.getWeekday());
            boolean available = true;

            for(String s: work_slots) {
                if(Intervals.isOverlap(target_slot, s)) {
                    available = false;
                    break;
                }
            }

            if(available) {
                available = false;
                for(String s: idle_slots) {
                    if(Intervals.isAinB(target_slot, s)) {
                        available = true;
                        break;
                    }
                }
            }

            if(available)
                resList.add(new Student(stuId, studentDao.findName(stuId)));
        }

        return resList;
    }

    public void addSchedule(WorkTimeInfo workTimeInfo) {
        String stuId = workTimeInfo.getStuId();
        String dptId = workTimeInfo.getDptId();
        String weekday = workTimeInfo.getWeekday();
        String workSlot = workTimeInfo.getInterval();
//        List<String> origSlots = scheduleDao.findStuDaySchedOfDpt(stuId, dptId, weekday);

//        if(!Intervals.validate(workTimeInfo.getInterval()))
//            return;
//
//        if(orig == null) {
//            intervals = workTimeInfo.getInterval();
//        }
//        else {
//            List<String> tmpList = new ArrayList<>(Arrays.asList(orig.split(";")));
//            tmpList.add(workTimeInfo.getInterval());
//            Collections.sort(tmpList);
//            for(String s: tmpList) {
//                intervals += (s + ";");
//            }
//        }

        Schedule newSchedule = new Schedule(stuId, dptId, weekday, workSlot);
        scheduleDao.insert(newSchedule);

//        if(orig != null)
//            scheduleDao.updateStuDaySchedOfDpt(newSchedule);
//        else
//            scheduleDao.insert(newSchedule);
    }

    public List<DetailInfo> getDptSchedOfWeekday(String dptId, String weekday) {
        List<Schedule> scheduleList;
        scheduleList = scheduleDao.list(dptId, weekday);

        if(scheduleList.isEmpty())
            return new ArrayList<>();

        return scheduleList.stream().map( x -> new DetailInfo(
                dptId,
                departmentDao.findName(dptId),
                x.getStuId(),
                studentDao.findName(x.getStuId()),
                "",
                x.getIntervals(),
                weekday
        )).collect(Collectors.toList());
    }

    public List<DetailInfo> getDptSchedOfDate(String dptId, Date date) {
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

        if((scheduleList = recChangeDao.find(dptId, date)).isEmpty()) {
            resList = getDptSchedOfWeekday(dptId, weekday);
            resList.stream().forEach( x -> x.setDate(date_str));
            return resList;
        }
        else {
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

    }

    public void deleteSchedule(WorkTimeInfo workTimeInfo) {
        scheduleDao.delete(new Schedule(
                workTimeInfo.getStuId(),
                workTimeInfo.getDptId(),
                workTimeInfo.getWeekday(),
                workTimeInfo.getInterval()));
    }

}
