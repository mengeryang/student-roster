package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Schedule;
import com.worksap.stm2017.domain.StuDptRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO SCHEDULE(stu_id,dpt_id,weekday,times) VALUES(?,?,?,?)";

    final static private String DELETE_SQL =
            "DELETE FROM SCHEDULE WHERE stu_id=? and dpt_id=? and weekday=?";

    final static private String LIST_SQL =
            "SELECT * FROM SCHEDULE WHERE dpt_id=? and weekday=?";

    final static private String FIND_STU_SCHED_OF_DPT_SQL =
            "SELECT times FROM SCHEDULE WHERE dpt_id=? and stu_id=? and weekday=?";

    final static private String UPDATE_STU_SCHED_OF_DPT_SQL =
            "UPDATE SCHEDULE SET times=? WHERE dpt_id=? and stu_id=? and weekday=?";

    final static private String FIND_STU_SCHED_OF_DAY_SQL =
            "SELECT times FROM SCHEDULE WHERE stu_id=? and weekday=?";

    @Autowired
    public ScheduleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Schedule schedule) {
        try {
            jdbcTemplate.update(INSERT_SQL,
                    ps -> {
                        ps.setString(1, schedule.getStuId());
                        ps.setString(2, schedule.getDptId());
                        ps.setString(3, schedule.getWeekday());
                        ps.setString(4, schedule.getIntervals());
                    });
        } catch (DataAccessException e) {
            //TODO
            System.out.println("ScheduleDaoImpl.java: insert: " + e.toString());
        }
    }

    public void delete(StuDptRel rel, String weekday) {
        jdbcTemplate.update(DELETE_SQL,
                ps -> {
            ps.setString(1, rel.getStuId());
            ps.setString(2, rel.getDptId());
            ps.setString(3, weekday);
                });
    }

    public List<Schedule> list(String dptId, String weekday) {
        return jdbcTemplate.query(LIST_SQL,
                ps -> {
            ps.setString(1, dptId);
            ps.setString(2, weekday);
                },
                (rs,numRow) -> new Schedule(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
    }

    public String findStuDaySchedOfDpt(StuDptRel rel, String weekday) {
        try {
            return jdbcTemplate.queryForObject(FIND_STU_SCHED_OF_DPT_SQL,
                    new Object[] {rel.getDptId(), rel.getStuId(), weekday},
                    (rs, i) -> rs.getString(1));
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void updateStuDaySchedOfDpt(Schedule schedule) {
        jdbcTemplate.update(UPDATE_STU_SCHED_OF_DPT_SQL,
                ps -> {
            ps.setString(1, schedule.getIntervals());
            ps.setString(2, schedule.getDptId());
            ps.setString(3, schedule.getStuId());
            ps.setString(4, schedule.getWeekday());
                });
    }

    public List<String> findStuSchedOfDay(String stuId, String weekday) {
        return jdbcTemplate.query(FIND_STU_SCHED_OF_DAY_SQL,
                ps -> {
            ps.setString(1, stuId);
            ps.setString(2, weekday);
                },
                (rs, i) -> rs.getString(1));
    }
}
