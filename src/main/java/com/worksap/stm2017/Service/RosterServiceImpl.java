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
        List<String> target_slots = info.getTimeSlots();

        for(String s: target_slots) {
            if (!Intervals.validate(s))
                return new ArrayList<>();
        }

        target_slots = Intervals.mergeList(target_slots);


        for(String stuId: stuIdList) {
            List<String> idle_slots = freeTimeDao.findByDay(stuId, weekday);
            List<String> work_slots = scheduleDao.findStuSchedOfDay(stuId, info.getWeekday());
            boolean available = true;

            for(String target_slot: target_slots) {
                for (String s : work_slots) {
                    if (Intervals.isOverlap(target_slot, s)) {
                        available = false;
                        break;
                    }
                }

                if (available) {
                    available = false;
                    for (String s : idle_slots) {
                        if (Intervals.isAinB(target_slot, s)) {
                            available = true;
                            break;
                        }
                    }
                }
                else
                    break;
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
        List<String> workSlots = workTimeInfo.getTimeSlots();
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
        for(String s: workSlots) {
            if(!Intervals.validate(s))
                return;
        }

        workSlots = Intervals.mergeList(workSlots);
        workSlots.stream().forEach( x -> scheduleDao.insert(new Schedule(stuId, dptId, weekday, x)));
//        Schedule newSchedule = new Schedule(stuId, dptId, weekday, workSlot);
//        scheduleDao.insert(newSchedule);

//        if(orig != null)
//            scheduleDao.updateStuDaySchedOfDpt(newSchedule);
//        else
//            scheduleDao.insert(newSchedule);
    }

    public List<WorkTimeInfo> getDptSchedOfWeekday(String dptId, String weekday) {
        List<String> stuIds = stuDptRelDao.list_by_dpt(dptId);
//        List<Schedule> scheduleList;
//        scheduleList = scheduleDao.list(dptId, weekday);
        List<WorkTimeInfo> resList = new ArrayList<>();

        for(String stuId: stuIds) {
            List<String> workSlots;
            workSlots = scheduleDao.findStuDaySchedOfDpt(stuId, dptId, weekday);
            if(!workSlots.isEmpty())
                resList.add(new WorkTimeInfo(stuId, dptId, weekday, new ArrayList<>(workSlots),""));
        }

        return resList;

//        if(scheduleList.isEmpty())
//            return new ArrayList<>();
//
//        return scheduleList.stream().map( x -> new DetailInfo(
//                dptId,
//                departmentDao.findName(dptId),
//                x.getId(),
//                studentDao.findName(x.getId()),
//                "",
//                x.getIntervals(),
//                weekday
//        )).collect(Collectors.toList());
    }

    public void addRecChange(WorkTimeInfo workTimeInfo, Date date, int status) {
        String stuId = workTimeInfo.getStuId();
        String dptId = workTimeInfo.getDptId();
        List<String> timeSlots = workTimeInfo.getTimeSlots();

        for(String s: timeSlots){
            if(!Intervals.validate(s))
                return;
        }

        timeSlots.stream().forEach(x -> recChangeDao.insert(new Schedule(stuId, dptId, "",x), date, status));
    }

    public List<WorkTimeInfo> getDptSchedOfDate(String dptId, Date date) {
        //TODO
        List<WorkTimeInfo> resList;
//        List<DetailInfo> resList = new ArrayList<>();
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String weekday;
//        String date_str = df.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                weekday = "1"; break;
            case Calendar.TUESDAY:
                weekday = "2"; break;
            case Calendar.WEDNESDAY:
                weekday = "3"; break;
            case Calendar.THURSDAY:
                weekday = "4"; break;
            case Calendar.FRIDAY:
                weekday = "5"; break;
            case Calendar.SATURDAY:
                weekday = "6"; break;
            default:
                weekday = "0";
        }

        resList = getDptSchedOfWeekday(dptId, weekday);
        for(WorkTimeInfo w: resList) {
            List<String> newSlots = new ArrayList<>();
            for(String s: w.getTimeSlots()){
                if(!recChangeDao.isDelete(new Schedule(w.getStuId(), w.getDptId(), w.getWeekday(), s), date))
                    newSlots.add(s);
            }
            w.setTimeSlots(newSlots);
        }
        return resList;
//        if((scheduleList = recChangeDao.(dptId, date)).isEmpty()) {
//            resList = getDptSchedOfWeekday(dptId, weekday);
//            resList.stream().forEach( x -> x.setDate(date_str));
//            return resList;
//        }
//        else {
//            return scheduleList.stream().map( x -> new DetailInfo(
//                dptId,
//                departmentDao.findName(dptId),
//                x.getId(),
//                studentDao.findName(x.getId()),
//                date_str,
//                x.getIntervals(),
//                weekday
//            )).collect(Collectors.toList());
//        }

    }

    public void deleteSchedule(WorkTimeInfo workTimeInfo) {
        String stuId = workTimeInfo.getStuId();
        String dptId = workTimeInfo.getDptId();
        String weekday = workTimeInfo.getWeekday();
        List<String> workSlots = workTimeInfo.getTimeSlots();

        workSlots.stream().forEach(x -> scheduleDao.delete(new Schedule(stuId, dptId, weekday,x)));
//        scheduleDao.delete(new Schedule(
//                workTimeInfo.getId(),
//                workTimeInfo.getDptId(),
//                workTimeInfo.getWeekday(),
//                workTimeInfo.getInterval()));
    }

}
