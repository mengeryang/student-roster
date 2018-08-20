package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier
@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_ID_COUNT_SQL =
            " SELECT COUNT(*) FROM USERS WHERE id = ?";
    private final static String INSERT_USER_INFO_SQL =
            "INSERT INTO USERS(id, name, password) VALUES(?,?,?)";
    private final static String FIND_BY_NAME_SQL =
            "SELECT * FROM USERS WHERE name = ?";
    private final static String FIND_BY_ID_SQL =
            "SELECT * FROM USERS WHERE id = ?";
    private final static String LIST_ALL_SQL =
            "SELECT * FROM USERS";

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchIdCount(String id) {
        return jdbcTemplate.queryForObject(MATCH_ID_COUNT_SQL,
                new Object[] {id},
                Integer.class);
    }


    public boolean insert(User user) {
        if(getMatchIdCount(user.getUserId()) > 0)
            return false;

        jdbcTemplate.update(INSERT_USER_INFO_SQL,
                ps -> { ps.setString(1, user.getUserId());
                        ps.setString(2, user.getUsername());
                        ps.setString(3, user.getPassword());});

        return true;
    }

    public List<User> findByName(String name) {
        return jdbcTemplate.query(FIND_BY_NAME_SQL,
                ps -> ps.setString(1, name),
                (rs, rowNum) -> new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
    }

    public User findById(String id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID_SQL,
                    new Object[]{id},
                    (rs, rowNum) -> new User(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3)));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> list_all() {
        return jdbcTemplate.query(LIST_ALL_SQL,
                (rs, rowNum) -> new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
    }
}
