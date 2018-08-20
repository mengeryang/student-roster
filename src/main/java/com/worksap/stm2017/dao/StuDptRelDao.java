package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.StuDptRel;

import java.util.List;

public interface StuDptRelDao {
    void insert(StuDptRel stuDptRel);
    void delete(StuDptRel stuDptRel);
    List<String> list_by_dpt(String dptid);
    List<String> list_by_stu(String uid);

}
