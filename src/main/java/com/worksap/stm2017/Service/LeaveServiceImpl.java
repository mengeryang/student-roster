package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.AskLeaveDao;
import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.DepartmentDao;
import com.worksap.stm2017.dao.StudentDao;
import com.worksap.stm2017.domain.Leave;
import com.worksap.stm2017.entity.LeaveForm;
import com.worksap.stm2017.entity.LeaveInfo;
import com.worksap.stm2017.entity.StuLeaveInfo;
import com.worksap.stm2017.util.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    private AskLeaveDao askLeaveDao;
    private StudentDao studentDao;
    private DepartmentDao departmentDao;

    @Autowired
    public LeaveServiceImpl(DaoFactory daoFactory) {
        this.askLeaveDao = daoFactory.getAskLeaveDao();
        this.studentDao = daoFactory.getStudentDao();
        this.departmentDao = daoFactory.getDepartmentDao();
    }

    public void AddLeaveApplication(LeaveForm leaveForm) {
        Date date;

        try {
            date = new SimpleDateFormat("MM-dd-yyyy").parse(leaveForm.getRawDate());
        } catch (ParseException e) {
            System.out.println(e.toString());
            return;
        }

        askLeaveDao.insert(new Leave(
                0,
                leaveForm.getAskId(),
                leaveForm.getReplaceId(),
                leaveForm.getDptId(),
                date,
                leaveForm.getTimeSlot(),
                0,
                Admin.NOT_DECIDE,
                leaveForm.getComment()
        ));
    }

    public List<LeaveInfo> listLeaveMsgForAdmin() {
        List<Leave> leaveList =  askLeaveDao.listByAdminStatus(Admin.NOT_DECIDE);
        List<LeaveInfo> infoList = new ArrayList<>();

        for(Leave rec: leaveList) {
            String stuId = rec.getAskId();
            String stuName = studentDao.findName(stuId);
            String dptName = departmentDao.findName(rec.getDptId());
            String date_str = new SimpleDateFormat("MM-dd-yyyy").format(rec.getDate());
            String tmp = stuName + "(" + stuId + ")" + " working at " + dptName + " wants to ask for a leave at " +
                    date_str + " " + rec.getTimeSlot();
            infoList.add(new LeaveInfo(rec.getId(), tmp, rec.getMsg()));
        }

        return infoList;
    }

    public List<StuLeaveInfo> listLeaveMsgForStu(String stuId) {
        List<Leave> leaveList = askLeaveDao.listByAskId(stuId);
        List<StuLeaveInfo> infoList = new ArrayList<>();

        for(Leave rec: leaveList) {
            String date_str = new SimpleDateFormat("MM-dd-yyyy").format(rec.getDate());
            String dptName = departmentDao.findName(rec.getDptId());
            String tmp = "Leave application for schedule (" + dptName + ": " + date_str + " " + rec.getTimeSlot() + ")";
            String status;
            switch(rec.getAdminStatus()){
                case Admin.NOT_DECIDE:
                    status = "Reviewing";
                    break;
                case Admin.ACCEPT:
                    status = "Approve";
                    break;
                default:
                    status = "Deny";
            }
            infoList.add(new StuLeaveInfo(rec.getId(),tmp,status));

        }
        return infoList;
    }

    public void updateAdminStatus(int msgId, int status) {
        askLeaveDao.updateAdminStatus(msgId, status);
    }

    public Leave findById(int id) {
        return askLeaveDao.findById(id);
    }
}
