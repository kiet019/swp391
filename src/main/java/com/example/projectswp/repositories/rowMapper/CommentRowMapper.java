package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        comment.setCommentID(rs.getInt("CommentID"));
        comment.setUserID(rs.getInt("UserID"));
        comment.setBlogID(rs.getInt("BlogID"));
        //comment.setItemID(rs.getInt("ItemID"));
        comment.setDateCreate(rs.getDate("DateCreate"));
        comment.setDateUpdate(rs.getDate("DateUpdate"));
        comment.setCommentContent(rs.getString("CommentContent"));

        return comment;
    }
}
