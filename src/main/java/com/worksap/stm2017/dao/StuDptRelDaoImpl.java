package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.StuDptRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StuDptRelDaoImpl implements StuDptRelDao {
    private JdbcTemplate jdbcTemplate;

    final static private String INSERT_REL_SQL =
            "INSERT INTO STU_DPT_REL(stu_id, dpt_id) VALUES(?,?)";
    final static private String DELETE_REL_SQL =
            "DELETE FROM STU_DPT_REL WHERE stu_id=? and dpt_id=?";
    final static private String LIST_BY_DPT_SQL =
            "SELECT stu_id FROM STU_DPT_REL WHERE dpt_id=?";
    final static private String LIST_BY_STU_SQL =
            "SELECT dpt_id FROM STU_DPT_REL WHERE stu_id=?";

    @Autowired
    public StuDptRelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(StuDptRel stuDptRel) {
        try {
            jdbcTemplate.update(INSERT_REL_SQL,
                    ps -> {
                        ps.setString(1, stuDptRel.getStuId());
                        ps.setString(2, stuDptRel.getDptId());
                    });
        } catch (DataAccessException e) {
            //TODO
        }
    }

    public void delete(StuDptRel stuDptRel) {
        jdbcTemplate.update(DELETE_REL_SQL,
                ps -> {
            ps.setString(1, stuDptRel.getStuId());
            ps.setString(2, stuDptRel.getDptId());
                });
    }

    public List<String> list_by_dpt(String dptId) {
        return jdbcTemplate.query(LIST_BY_DPT_SQL,
                ps -> ps.setString(1, dptId),
                (rs, numRow) -> rs.getString(1));
    }

    public List<String> list_by_stu(String stuId) {
        return jdbcTemplate.query(LIST_BY_STU_SQL,
                ps -> ps.setString(1, stuId),
                (rs, numRow) -> rs.getString(1));
    }
}
