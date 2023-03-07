package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Reply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyRowMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reply reply = new Reply();

        reply.setReplyId(rs.getInt("ReplyID"));
        reply.setCommentId(rs.getInt("CommentID"));
        reply.setUserID(rs.getInt("UserID"));
        reply.setDateCreate(rs.getDate("DateCreate"));
        reply.setDateUpdate(rs.getDate("DateUpdate"));
        reply.setReplyContent(rs.getString("ReplyContent"));

        return reply;
    }
}
