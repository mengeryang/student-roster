package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.FreeTimeDao;
import com.worksap.stm2017.dao.StuDptRelDao;
import com.worksap.stm2017.dao.StudentDao;
import com.worksap.stm2017.domain.FreeTime;
import com.worksap.stm2017.domain.StuDptRel;
import com.worksap.stm2017.domain.Student;
import com.worksap.stm2017.entity.FreeTimeInfo;
import com.worksap.stm2017.entity.StuRegInfo;
import com.worksap.stm2017.util.Intervals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void setFreeTime(FreeTimeInfo freeTimeInfo) {
        if(Intervals.validate(freeTimeInfo.getIntervals())) {
            freeTimeDao.deleteByDay(freeTimeInfo.getStuId(), freeTimeInfo.getWeekday());
            freeTimeDao.insert(new FreeTime(
                    freeTimeInfo.getStuId(),
                    freeTimeInfo.getWeekday(),
                    Intervals.merge(freeTimeInfo.getIntervals())));
        }
    }
}
