package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.*;
import com.worksap.stm2017.domain.Account;
import com.worksap.stm2017.domain.FreeTime;
import com.worksap.stm2017.domain.StuDptRel;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.FreeTimeInfo;
import com.worksap.stm2017.entity.LoginInfo;
import com.worksap.stm2017.entity.StuInfo;
import com.worksap.stm2017.entity.StuRegForm;
import com.worksap.stm2017.util.Intervals;
import com.worksap.stm2017.util.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;
    private StuDptRelDao stuDptRelDao;
    private FreeTimeDao freeTimeDao;
    private AccountDao accountDao;

    @Autowired
    public StudentServiceImpl(DaoFactory daoFactory) {
        this.studentDao = daoFactory.getStudentDao();
        this.stuDptRelDao = daoFactory.getStuDptRelDao();
        this.freeTimeDao = daoFactory.getFreeTimeDao();
        this.accountDao = daoFactory.getAccountDao();
    }

    public void register(StuRegForm stuRegForm) {
        studentDao.insert(new Student(stuRegForm.getStuId(), stuRegForm.getStuName()));
        stuDptRelDao.insert(new StuDptRel(stuRegForm.getStuId(), stuRegForm.getDptId()));
        accountDao.insert(new Account(stuRegForm.getStuId(), stuRegForm.getStuId()));
    }

    public void updateAccount(LoginInfo newAccount) {
        accountDao.update(new Account(newAccount.getId(), newAccount.getPassword()));
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
        newSlots = Intervals.mergeList(newSlots);
        freeTimeDao.deleteByDay(stuId, weekday);
        newSlots.stream().forEach( x -> freeTimeDao.insert(new FreeTime(stuId, weekday, x)));

    }

    public void deleteFreeTime(FreeTimeInfo freeTimeInfo) {
        String weekday = freeTimeInfo.getWeekday();
        String stuId = freeTimeInfo.getStuId();

        freeTimeInfo.getTimeSlots().forEach(x -> freeTimeDao.delete(new FreeTime(stuId, weekday, x)));
    }

    public FreeTimeInfo listFreeTimeOfDay(String stuId, String weekday) {
        List<String> freeTimes = freeTimeDao.findByDay(stuId, weekday);
        if(studentDao.findName(stuId) == null)
            return null;

        Collections.sort(freeTimes);
        return new FreeTimeInfo(stuId, weekday, freeTimes);
    }

    public List<FreeTimeInfo> listFreeTime(String stuId) {
        List<FreeTimeInfo> res = new ArrayList<>();

        if(studentDao.findName(stuId) == null)
            return new ArrayList<>();

        for(String weekday: WeekDay.WEEKDAYS) {
            res.add(listFreeTimeOfDay(stuId, weekday));
        }

        return res;
    }
}
