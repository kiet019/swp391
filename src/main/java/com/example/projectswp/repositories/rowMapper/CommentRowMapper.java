package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
