package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Reply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyRowMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
