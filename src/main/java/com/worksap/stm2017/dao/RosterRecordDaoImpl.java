package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.RosterRecord;
import com.worksap.stm2017.domain.UserWorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RosterRecordDaoImpl implements RosterRecordDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_REC_SQL =
            "INSERT INTO ROSTERREC(userid,dptid,date,sh,sm,eh,em)" +
                    "VALUES(?,?,?,?,?,?,?)";
    final static private String LIST_DEPREC_SQL =
            "SELECT userid,sh,sm,eh,em FROM ROSTERREC WHERE dptid=? and date=?";

    @Autowired
    public RosterRecordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(RosterRecord rec) {
        jdbcTemplate.update(INSERT_REC_SQL,
                ps -> {
            ps.setString(1, rec.getUserId());
            ps.setString(2, rec.getDepartmentId());
            ps.setDate(3, new java.sql.Date(rec.getDate().getTime()));
            ps.setInt(4, rec.getSh());
            ps.setInt(5, rec.getSm());
            ps.setInt(6, rec.getEh());
            ps.setInt(7, rec.getEm());
                });
    }

    public List<UserWorkTime> list_dpt_rec(String dptId, Date date) {
        return jdbcTemplate.query(LIST_DEPREC_SQL,
                ps -> {
            ps.setString(1, dptId);
            ps.setDate(2, new java.sql.Date(date.getTime()));
                },
                (rs, numRow) -> new UserWorkTime(
                        rs.getString(1),
                        dptId,
                        null,
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)
                ));
    }
}
