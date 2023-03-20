package com.example.projectswp.repositories;

import com.example.projectswp.model.Items;
import com.example.projectswp.model.Request;
import com.example.projectswp.repositories.rowMapper.RequestRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestRepository {
    public static final RequestRowMapper REQUEST_ROW_MAPPER = new RequestRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Request> getRequestByItemId(int itemId) {
        String sql = "SELECT * FROM dbo.Requests WHERE ItemID = ?";
        List<Request> requestList = jdbcTemplate.query(sql, REQUEST_ROW_MAPPER, itemId);
        return requestList;
    }
    public List<Request> getRequestByUserId(int userId) {
        String sql = "SELECT * FROM dbo.Requests WHERE UserID = ? ";
        List<Request> requestList = jdbcTemplate.query(sql, REQUEST_ROW_MAPPER, userId);
        return requestList;
    }

    public Request getRequestByID(int requestID) {
        String sql = "SELECT * FROM dbo.Requests WHERE RequestID = ?";
        List<Request> requestList= jdbcTemplate.query(sql,REQUEST_ROW_MAPPER,requestID);
        return requestList.size() != 0 ? requestList.get(0) : null;
    }

    public boolean updateStatus(int requestId,int status) {
        String sql = "UPDATE dbo.Requests SET Status = ?, DateChangeStatus = ? WHERE RequestID = ?";
        int rowAffected = jdbcTemplate.update(sql,status, Ultil.getCurrentDate(), requestId);
        return rowAffected > 0;
    }


}
