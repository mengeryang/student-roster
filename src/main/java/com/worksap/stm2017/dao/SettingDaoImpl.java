package com.worksap.stm2017.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SettingDaoImpl implements SettingDao {
    private JdbcTemplate jdbcTemplate;

    final static private String SET_SETFREE_SQL =
            "UPDATE SETTINGS SET set_free_time=? WHERE setting_id=0";
    final static private String SET_WORKLOAD_SQL =
            "UPDATE SETTINGS SET workload=? WHERE setting_id=0";
    final static private String GET_SETFREE_SQL =
            "SELECT set_free_time FROM SETTINGS WHERE setting_id=0";
    final static private String GET_WORKLOAD_SQL =
            "SELECT workload FROM SETTINGS WHERE setting_id=0";

    @Autowired
    public SettingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSetFree(boolean flag) {
        jdbcTemplate.update(SET_SETFREE_SQL,
                ps -> ps.setBoolean(1, flag));
    }

    public void setWorkload(int workload) {
        jdbcTemplate.update(SET_WORKLOAD_SQL,
                ps -> ps.setInt(1, workload));
    }

    public boolean getSetFree() {
        return jdbcTemplate.queryForObject(GET_SETFREE_SQL, Boolean.class);
    }

    public int getWorkload() {
        return jdbcTemplate.queryForObject(GET_WORKLOAD_SQL, Integer.class);
    }
}
