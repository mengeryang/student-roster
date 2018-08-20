package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Student;

public interface StudentDao {
    void insert(Student student);
    void delete(String id);
    String findName(String id);
}
