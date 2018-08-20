package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.UserWorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserWorkTimeDaoImpl implements UserWorkTimeDao {
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_WORK_SQL =
            "INSERT INTO WORK_TIME(userid,dptid,day,sh,sm,eh,em) VALUES(?,?,?,?,?,?,?)";

    private final static String DELETE_WORK_SQL =
            "DELETE FROM WORK_TIME WHERE userid=? and dptid=? and weekday=?";

    private final static String LIST_BY_USER_SQL =
            "SELECT * FROM WORK_TIME WHERE userid=? and day=?";

    private final static String LIST_BY_DPT_SQL =
            "SELECT * FROM WORK_TIME WHERE dptid=? and day=?";

    @Autowired
    public UserWorkTimeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(UserWorkTime workTime) {
        jdbcTemplate.update(INSERT_WORK_SQL,
                ps -> {
            ps.setString(1, workTime.getUserId());
            ps.setString(2, workTime.getDptId());
            ps.setString(3, workTime.getWeekday());
            ps.setInt(4, workTime.getStart_h());
            ps.setInt(5, workTime.getStart_m());
            ps.setInt(6, workTime.getEnd_h());
            ps.setInt(7, workTime.getEnd_m());
        });
    }

    public void delete(UserWorkTime workTime) {
        jdbcTemplate.update(DELETE_WORK_SQL,
                ps -> {
            ps.setString(1, workTime.getUserId());
            ps.setString(2, workTime.getDptId());
            ps.setString(3, workTime.getWeekday());
                });
    }

    public List<UserWorkTime> listByUser(String userid, String weekday) {
        return jdbcTemplate.query(LIST_BY_USER_SQL,
                ps -> {
            ps.setString(1, userid);
            ps.setString(2, weekday);
                },
                (rs, rowNum) -> new UserWorkTime(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));
    }

    public List<UserWorkTime> listByDpt(String dptid, String weekday) {
        return jdbcTemplate.query(LIST_BY_DPT_SQL,
                ps -> {
                    ps.setString(1, dptid);
                    ps.setString(2, weekday);
                },
                (rs, rowNum) -> new UserWorkTime(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));
    }




}
