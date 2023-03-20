package com.example.projectswp.repositories;

import com.example.projectswp.data_view_model.comment.CommentCreateVM;
import com.example.projectswp.data_view_model.comment.CommentIdVM;
import com.example.projectswp.data_view_model.comment.CommentUpdateVM;
import com.example.projectswp.model.Comment;
import com.example.projectswp.model.Reply;
import com.example.projectswp.repositories.rowMapper.BlogCategoryRowMapper;
import com.example.projectswp.repositories.rowMapper.CommentRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    private static final CommentRowMapper COMMENT_ROW_MAPPER = new CommentRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ReplyRepository replyRepository;

    public List<Comment> getComments() {
        String sql = "SELECT * FROM dbo.Comments";
        List<Comment> listComment = jdbcTemplate.query(sql, COMMENT_ROW_MAPPER);
        for (Comment comment : listComment){
            comment.setListReply(replyRepository.getReplyByCommentID(comment.getCommentID()));
        }
        return  listComment.size() > 0 ? listComment: null;
    }
    public List<Comment> getCommentByBlogId(int blogId) {
        String sql = "SELECT * FROM dbo.Comments c WHERE c.BlogID = ?";
        List<Comment> listComment = jdbcTemplate.query(sql,COMMENT_ROW_MAPPER, blogId);
        for (Comment comment : listComment){
            comment.setListReply(replyRepository.getReplyByCommentID(comment.getCommentID()));
        }
        return  listComment.size() > 0 ? listComment: null;
    }

    public List<Comment> getCommentByItemId(int itemID) {
        String sql = "SELECT * FROM dbo.Comments c WHERE c.ItemID = ?";
        List<Comment> listComment = jdbcTemplate.query(sql,COMMENT_ROW_MAPPER, itemID);
        for (Comment comment : listComment){
            comment.setListReply(replyRepository.getReplyByCommentID(comment.getCommentID()));
        }
        return  listComment.size() > 0 ? listComment: null;
    }

    public boolean updateComment(int uid, CommentUpdateVM comemtUpdate) {
        String sql = "UPDATE dbo.Comments SET CommentContent = ?, DateUpdate = ? WHERE  UserID = ? and CommentID = ?";
        int rowAffected = jdbcTemplate.update(sql, comemtUpdate.getCommentContent(), Ultil.getCurrentDate(), uid, comemtUpdate.getCommentId());
        return rowAffected > 0;
    }

    public boolean createComment(int uid, CommentCreateVM cmt) {
        String sql = "INSERT INTO dbo.Comments(UserID, BlogID, ItemID, DateCreate, CommentContent) VALUES (?, ?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql, uid, cmt.getBlogId() != 0 ? cmt.getBlogId() : null,
                cmt.getItemId() != 0 ? cmt.getItemId() : null, Ultil.getCurrentDate(), cmt.getCommentContent());
        return rowAffected > 0;
    }
    public boolean deleteComment(int uid, int commentId) {
        String sql = "DELETE dbo.Comments WHERE  UserID = ? and CommentID = ?";
        int rowAffected = jdbcTemplate.update(sql, uid, commentId);
        return rowAffected > 0;
    }
}
