package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.DepartmentDao;
import com.worksap.stm2017.dao.StuDptRelDao;
import com.worksap.stm2017.domain.StuDptRel;
import com.worksap.stm2017.entity.DptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;
    private StuDptRelDao stuDptRelDao;

    @Autowired
    public DepartmentServiceImpl(DaoFactory daoFactory) {
        this.departmentDao = daoFactory.getDepartmentDao();
        this.stuDptRelDao = daoFactory.getStuDptRelDao();
    }

    public List<DptInfo> list_all_dpt() {
        return departmentDao.list_all().stream().map( x -> new DptInfo(x.getId(), x.getName())).collect(Collectors.toList());
    }

    public List<DptInfo> listDptOfStu(String stuId) {
        return stuDptRelDao.list_by_stu(stuId).stream()
                .map(x -> new DptInfo(x, departmentDao.findName(x)))
                .collect(Collectors.toList());
    }
}
