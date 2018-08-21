package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.FreeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FreeTimeDaoImpl implements FreeTimeDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO FREE_TIME(stu_id,weekday,time_slot) VALUES(?,?,?)";

    final static private String DELETE_FREE_TIME_OF_DAY_SQL =
            "DELETE FROM FREE_TIME WHERE stu_id=? and weekday=?";

    final static private String FIND_FREE_TIME_OF_DAY_SQL =
            "SELECT time_slot FROM FREE_TIME WHERE stu_id=? and weekday=?";

    final static private String DELETE_SQL =
            "DELETE FROM FREE_TIME WHERE stu_id=? and weekday=? and time_slot=?";

    @Autowired
    public FreeTimeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(FreeTime freeTime) {
        try {
            jdbcTemplate.update(INSERT_SQL,
                    ps -> {
                        ps.setString(1, freeTime.getStuId());
                        ps.setString(2, freeTime.getWeekday());
                        ps.setString(3, freeTime.getTimeSlot());
                    });
        } catch (DataAccessException e) {
            //TODO
            System.out.println("FreeTimeDaoImpl.java: insert: " + e.toString());
        }
    }

    public void deleteByDay(String stuId, String weekday) {
        jdbcTemplate.update(DELETE_FREE_TIME_OF_DAY_SQL,
                ps -> {
            ps.setString(1, stuId);
            ps.setString(2, weekday);
                });
    }

    public void delete(FreeTime freeTime) {
        jdbcTemplate.update(DELETE_SQL,
                ps -> {
            ps.setString(1, freeTime.getStuId());
            ps.setString(2, freeTime.getWeekday());
            ps.setString(3, freeTime.getTimeSlot());
                });
    }

    public List<String> findByDay(String stuId, String weekday) {
        try {
            return jdbcTemplate.query(FIND_FREE_TIME_OF_DAY_SQL,
                    new Object[]{stuId, weekday},
                    (rs, numRow) -> rs.getString(1));
        } catch (DataAccessException e) {
            //TODO
            System.out.println("FreeTimeDaoImpl.java: findByDay: " + e.toString());
        }

        return new ArrayList<>();
    }
}
