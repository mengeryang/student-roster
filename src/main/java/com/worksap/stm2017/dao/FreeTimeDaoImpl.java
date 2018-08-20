package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.FreeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FreeTimeDaoImpl implements FreeTimeDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO FREE_TIME(stu_id,weekday,times) VALUES(?,?,?)";

    final static private String DELETE_BY_DAY_SQL =
            "DELETE FROM FREE_TIME WHERE stu_id=? and weekday=?";

    final static private String FIND_BY_DAY_SQL =
            "SELECT times FROM FREE_TIME WHERE stu_id=? and weekday=?";

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
                        ps.setString(3, freeTime.getIntervals());
                    });
        } catch (DataAccessException e) {
            //TODO
        }
    }

    public void deleteByDay(String stuId, String weekday) {
        jdbcTemplate.update(DELETE_BY_DAY_SQL,
                ps -> {
            ps.setString(1, stuId);
            ps.setString(2, weekday);
                });
    }

    public String findByDay(String stuId, String weekday) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_DAY_SQL,
                    new Object[]{stuId, weekday},
                    (rs, numRow) -> rs.getString(1));
        } catch (DataAccessException e) {
            //TODO
        }

        return null;
    }
}
