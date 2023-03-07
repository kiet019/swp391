package com.example.projectswp.repositories;

import com.example.projectswp.model.Reply;
import com.example.projectswp.repositories.rowMapper.ReplyRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ReplyRepository {
    private static final ReplyRowMapper REPLY_ROW_MAPPER = new ReplyRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getUserIDbyReplyID(Reply reply) {
        String sql = "select * from dbo.Replies where [ReplyID] = ?";
        List<Reply> list = jdbcTemplate.query(sql, REPLY_ROW_MAPPER, reply.getReplyId());
        return list.size() >0? list.get(0).getUserID() : 0;
    }
    public List<Reply> getReplyByCommentID(int commentID) {
        String sql = "select * from dbo.Replies where CommentID = ?";
        List<Reply> list = jdbcTemplate.query(sql, REPLY_ROW_MAPPER, commentID);
        return list.size() >0 ? list : null;
    }

    public boolean insertReply(Reply reply, int userID) {
        String sql = "insert into dbo.Replies([CommentID], [UserID], [DateCreate], [ReplyContent])\n" +
                "values(?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, reply.getCommentId(), userID, Ultil.getCurrentDate(), reply.getReplyContent());
        return result > 0;
    }

    public boolean updateReply(Reply reply, int userID) {
        String sql = "update dbo.Replies \n" +
                "set [ReplyContent] = ?,\n" +
                "\t[DateUpdate] = ?\n" +
                "where [ReplyID]= ? AND [UserID] = ?";
        int result = jdbcTemplate.update(sql, reply.getReplyContent(), Ultil.getCurrentDate(), reply.getReplyId(), userID);
        return result>0;
    }

    public boolean deleteReply(Reply reply, int userID) {
        String sql = "delete from dbo.Replies where [ReplyID]= ? AND [UserID] = ?";
        int result = jdbcTemplate.update(sql, reply.getReplyId(), userID);
        return result>0;
    }


}
