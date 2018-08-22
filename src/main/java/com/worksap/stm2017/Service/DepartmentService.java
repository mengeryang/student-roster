package com.worksap.stm2017.Service;

import com.worksap.stm2017.entity.DptInfo;

import java.util.List;

public interface DepartmentService {
    List<DptInfo> list_all_dpt();
    List<DptInfo> listDptOfStu(String stuId);
}
