package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Request;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {
    @Override
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
        Request request = new Request();

        request.setRequestID(rs.getInt("RequestID"));
        request.setUserID(rs.getInt("UserID"));
        request.setItemQuantity(rs.getInt("ItemQuantity"));
        request.setItemID(rs.getInt("ItemID"));
        request.setAddress(rs.getString("Address"));
        request.setNote(rs.getString("Note"));
        request.setDateCreate(rs.getDate("DateCreate"));
        request.setStatus(rs.getInt("Status"));
        request.setDateChangeStatus(rs.getDate("DateChangeStatus"));

        return request;
    }
}
