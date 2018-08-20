package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Schedule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RecChangeDaoImpl implements RecChangeDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO CHANGE_REC(stu_id,dpt_id,weekday,times,rec_date) VALUES(?,?,?,?,?)";

    final static private String DELETE_SQL =
            "DELETE FROM CHANGE_REC WHERE dpt_id=? and rec_date=?";

    final static private String FIND_SQL =
            "SELECT stu_id,dpt_id,weekday,times FROM CHANGE_REC WHERE dpt_id=? and rec_date=?";

    public RecChangeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Schedule schedule, Date date) {
        try {
            jdbcTemplate.update(INSERT_SQL,
                    ps -> {
                        ps.setString(1, schedule.getStuId());
                        ps.setString(2, schedule.getDptId());
                        ps.setString(3, schedule.getWeekday());
                        ps.setString(4, schedule.getIntervals());
                        ps.setDate(5, new java.sql.Date(date.getTime()));
                    });
        } catch (DataAccessException e) {
            //TODO
        }
    }

    public void delete(String dptId, Date date) {
        jdbcTemplate.update(DELETE_SQL,
                ps -> {
            ps.setString(1, dptId);
            ps.setDate(2, new java.sql.Date(date.getTime()));
                });
    }

    public List<Schedule> find(String dptId, Date date) {
        return jdbcTemplate.query(FIND_SQL,
                ps -> {
            ps.setString(1, dptId);
            ps.setDate(2, new java.sql.Date(date.getTime()));
                },
                (rs, numRow) -> new Schedule(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
    }
}
