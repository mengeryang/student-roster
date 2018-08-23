package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AskLeaveDaoImpl implements AskLeaveDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO LEAVE_REC(ask_id, replace_id, dpt_id, rec_date, time_slot, " +
                    "replace_status, admin_status, msg) VALUES(?,?,?,?,?,?,?,?)";
    final static private String LIST_BY_ASK_ID_SQL =
            "SELECT * FROM LEAVE_REC WHERE ask_id=?";
    final static private String LIST_BY_ADMIN_STATUS_SQL =
            "SELECT * FROM LEAVE_REC WHERE admin_status=?";
    final static private String UPDATE_ADMIN_STATUS_BY_ID_SQL =
            "UPDATE LEAVE_REC SET admin_status=? WHERE rec_id=?";
    final static private String FIND_BY_ID_SQL =
            "SELECT * FROM LEAVE_REC WHERE rec_id=?";

    @Autowired
    public AskLeaveDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Leave info) {
        jdbcTemplate.update(INSERT_SQL,
                ps -> {
            ps.setString(1, info.getAskId());
            ps.setString(2, info.getReplaceId());
            ps.setString(3, info.getDptId());
            ps.setDate(4, new java.sql.Date(info.getDate().getTime()));
            ps.setString(5, info.getTimeSlot());
            ps.setInt(6, info.getReplaceStatus());
            ps.setInt(7, info.getAdminStatus());
            ps.setString(8, info.getMsg());
                });
    }

    public List<Leave> listByAskId(String askId) {
        return jdbcTemplate.query(LIST_BY_ASK_ID_SQL,
                new Object[] {askId},
                (rs, i) -> new Leave(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9)
                ));
    }

    public List<Leave> listByAdminStatus(int status) {
        return jdbcTemplate.query(LIST_BY_ADMIN_STATUS_SQL,
                new Object[] {status},
                (rs, i) -> new Leave(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9)
                ));
    }

    public void updateAdminStatus(int id, int status) {
        jdbcTemplate.update(UPDATE_ADMIN_STATUS_BY_ID_SQL,
                ps -> {
            ps.setInt(1, status);
            ps.setInt(2, id);
                });
    }

    public Leave findById(int id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_SQL,
                new Object[] {id},
                (rs, i) -> new Leave(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9)
                ));
    }
}
