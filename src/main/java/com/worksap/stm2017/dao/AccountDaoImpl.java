package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_SQL =
            "INSERT INTO ID_PASSWORD(user_id, user_password) VALUES(?,?)";
    final static private String MATCH_SQL =
            "SELECT COUNT(*) FROM ID_PASSWORD where user_id=? and user_password=?";
    final static private String UPDATE_SQL =
            "UPDATE ID_PASSWORD SET user_password=? WHERE user_id=?";

    @Autowired
    public AccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Account account) {
        jdbcTemplate.update(INSERT_SQL,
                ps -> {
            ps.setString(1, account.getId());
            ps.setString(2, account.getPassword());
                });
    }

    public int matchCount(Account account) {
        return jdbcTemplate.queryForObject(MATCH_SQL,
                new Object[] {account.getId(), account.getPassword()},
                Integer.class);
    }

    public void update(Account account) {
        jdbcTemplate.update(UPDATE_SQL,
                ps -> {
            ps.setString(2, account.getId());
            ps.setString(1, account.getPassword());
                });
    }
}
