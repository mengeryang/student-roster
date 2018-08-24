package com.worksap.stm2017.Service;

import com.worksap.stm2017.domain.Leave;
import com.worksap.stm2017.entity.LeaveForm;
import com.worksap.stm2017.entity.LeaveInfo;
import com.worksap.stm2017.entity.StuLeaveInfo;

import java.util.List;

public interface LeaveService {
    void AddLeaveApplication(LeaveForm leaveForm);
    List<LeaveInfo> listLeaveMsgForAdmin();
    void updateAdminStatus(int msgId, int status);
    Leave findById(int id);
    List<StuLeaveInfo> listLeaveMsgForStu(String stuId);
}
