package org.dcsc.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Deprecated
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DcscUser get(int id) {
        String SQL = "SELECT * FROM dcsc_accounts.dcsc_users WHERE id = " + id;

        return jdbcTemplate.queryForObject(SQL, (ResultSet result, int rowNum) -> {
            DcscUser user = new DcscUser();
            user.setId(result.getInt("id"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setEnabled(result.getBoolean("is_enabled"));
            user.setLocked(result.getBoolean("is_locked"));

            return user;
        });
    }
}
