package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.FreeTimeDao;
import com.worksap.stm2017.dao.StuDptRelDao;
import com.worksap.stm2017.dao.StudentDao;
import com.worksap.stm2017.domain.FreeTime;
import com.worksap.stm2017.domain.StuDptRel;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.FreeTimeInfo;
import com.worksap.stm2017.entity.StuInfo;
import com.worksap.stm2017.entity.StuRegInfo;
import com.worksap.stm2017.util.Intervals;
import com.worksap.stm2017.util.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;
    private StuDptRelDao stuDptRelDao;
    private FreeTimeDao freeTimeDao;

    @Autowired
    public StudentServiceImpl(DaoFactory daoFactory) {
        this.studentDao = daoFactory.getStudentDao();
        this.stuDptRelDao = daoFactory.getStuDptRelDao();
        this.freeTimeDao = daoFactory.getFreeTimeDao();
    }

    public void register(StuRegInfo stuRegInfo) {
        studentDao.insert(new Student(stuRegInfo.getStuId(), stuRegInfo.getStuName()));
        stuDptRelDao.insert(new StuDptRel(stuRegInfo.getStuId(), stuRegInfo.getDptId()));
    }

    public List<StuInfo> list_all_stu() {
        return studentDao.list().stream().map(x -> new StuInfo(x.getId(), x.getName())).collect(Collectors.toList());
    }

    public void addFreeTime(FreeTimeInfo freeTimeInfo) {
        List<String> newSlots = freeTimeInfo.getTimeSlots();
        String weekday = freeTimeInfo.getWeekday();
        String stuId = freeTimeInfo.getStuId();

        for(String s: newSlots) {
            if(!Intervals.validate(s)) {
                System.out.println(this.getClass() + ": invalid time-slot format.");
                return;
            }
        }

        newSlots.addAll(freeTimeDao.findByDay(stuId, weekday));
        Intervals.mergeList(newSlots);
        newSlots.stream().forEach( x -> freeTimeDao.insert(new FreeTime(stuId, weekday, x)));


//        if(Intervals.validate(freeTimeInfo.getIntervals())) {
//            freeTimeDao.deleteByDay(freeTimeInfo.getId(), freeTimeInfo.getWeekday());
//            freeTimeDao.insert(new FreeTime(
//                    freeTimeInfo.getId(),
//                    freeTimeInfo.getWeekday(),
//                    Intervals.merge(freeTimeInfo.getIntervals())));
//        }
    }

    public FreeTimeInfo listFreeTimeOfDay(String stuId, String weekday) {
        List<String> freeTimes = freeTimeDao.findByDay(stuId, weekday);
        return new FreeTimeInfo(stuId, weekday, freeTimes);
    }

    public List<FreeTimeInfo> listFreeTime(String stuId) {
        List<FreeTimeInfo> res = new ArrayList<>();

        for(String weekday: WeekDay.WEEKDAYS) {
            res.add(listFreeTimeOfDay(stuId, weekday));
        }

        return res;
    }
}
