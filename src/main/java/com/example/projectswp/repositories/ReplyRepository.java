package com.example.projectswp.repositories;

import com.example.projectswp.repositories.rowMapper.ReplyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyRepository {
    private static final ReplyRowMapper RELY_ROW_MAPPER = new ReplyRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
}
