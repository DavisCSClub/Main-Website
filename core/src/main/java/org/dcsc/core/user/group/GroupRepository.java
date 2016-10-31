package org.dcsc.core.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Deprecated
@Repository
public class GroupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Group get(int id) {
        final String QUERY = "SELECT * FROM dcsc_accounts.dcsc_groups WHERE id = " + id;
        return query(QUERY);
    }

    public Group get(String name) {
        final String QUERY = "SELECT * FROM dcsc_accounts.dcsc_groups WHERE name = " + name;
        return query(QUERY);
    }

    private Group query(String query) {
        return jdbcTemplate.queryForObject(query, (ResultSet result, int rowNum) -> {
            Group group = new Group();
            group.setId(result.getInt("id"));
            group.setName(result.getString("name"));
            return group;
        });
    }
}
