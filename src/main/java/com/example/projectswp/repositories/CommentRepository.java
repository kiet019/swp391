package com.example.projectswp.repositories;

import com.example.projectswp.repositories.rowMapper.BlogCategoryRowMapper;
import com.example.projectswp.repositories.rowMapper.CommentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {
    private static final CommentRowMapper COMMENT_ROW_MAPPER = new CommentRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
}
