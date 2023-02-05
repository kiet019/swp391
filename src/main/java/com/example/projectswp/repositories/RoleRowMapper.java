package com.example.projectswp.repositories;

import com.example.projectswp.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("RoleID"));
        role.setName(rs.getString("Role_Name"));
        return role;
    }
}
