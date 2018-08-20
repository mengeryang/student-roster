package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_DEPART_SQL =
            "INSERT INTO DEPARTMENT(dpt_id, dpt_name) VALUES(?,?)";
    final static private String FIND_BY_ID_SQL =
            "SELECT dpt_name FROM DEPARTMENT WHERE dpt_id=?";
    final static private String LIST_ALL_SQL =
            "SELECT * FROM DEPARTMENT";

    @Autowired
    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Department department) {
        try {
            jdbcTemplate.update(INSERT_DEPART_SQL,
                    ps -> {
                        ps.setString(1, department.getId());
                        ps.setString(2, department.getName());
                    });
        } catch (DataAccessException e) {
            //TODO
            System.out.println("DepartmentDaoImpl.java: insert(): " + e.toString());
        }
    }

    public String findName(String dptId) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_SQL,
                new Object[] {dptId},
                (rs, numRow) -> rs.getString(1));
    }

    public List<Department> list_all() {
        return jdbcTemplate.query(LIST_ALL_SQL,
                (rs, numRow) -> new Department(
                        rs.getString(1),
                        rs.getString(2)));
    }
}
