package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO STUDENT(stu_id, stu_name) VALUES(?,?)";

    final static private String DELETE_SQL =
            "DELETE FROM STUDENT WHERE stu_id=?";

    final static private String FIND_NAME_BY_ID =
            "SELECT stu_name FROM STUDENT WHERE stu_id=?";

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Student student) {
        try {
            jdbcTemplate.update(INSERT_SQL,
                    ps -> {
                        ps.setString(1, student.getId());
                        ps.setString(2, student.getName());
                    });
        } catch (DataAccessException e) {
            //TODO
            System.out.println("StudentDaoImpl.java: insert(): " + e.toString());
        }
    }

    public void delete(String id) {
        jdbcTemplate.update(DELETE_SQL,
                ps -> ps.setString(1, id));
    }

    public String findName(String id) {
        try {
            return jdbcTemplate.queryForObject(FIND_NAME_BY_ID,
                    new Object[]{id},
                    (rs, numRow) -> rs.getString(1));
        } catch (DataAccessException e) {
            //TODO
            System.out.println("StudentDaoImpl.java: findName: " + e.toString());
        }

        return null;
    }
}
