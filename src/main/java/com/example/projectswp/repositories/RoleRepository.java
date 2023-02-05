package com.example.projectswp.repositories;

import com.example.projectswp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
    private static final RoleRowMapper ROLE_ROW_MAPPER = new RoleRowMapper();

    @Autowired
    JdbcTemplate jdbcTemplate ;

    public Role getRole(int id) {
        String sql = "select * from Roles where RoleID = ?";

        List<Role> list = jdbcTemplate.query(sql, ROLE_ROW_MAPPER, id);

        return list.size() == 0 ? null : list.get(0);
    }
}
