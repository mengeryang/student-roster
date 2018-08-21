package com.worksap.stm2017.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate jdbcTemplate;

    final static private String MATCH_COUNT_SQL =
            "SELECT COUNT(*) FROM ADMIN_ID WHERE admin_id=?";

    @Autowired
    public AdminDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int matchCount(String adminId) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[] {adminId}, Integer.class);
    }

}
