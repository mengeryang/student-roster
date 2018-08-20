package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department department);
    String findName(String dptId);
    List<Department> list_all();
}
