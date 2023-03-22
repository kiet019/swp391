package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setOrderID(rs.getInt("OrderID"));
        order.setUserID(rs.getInt("UserID"));
        order.setItemID(rs.getInt("ItemID"));
        order.setQuanlity(rs.getInt("Quanlity"));
        order.setAddress(rs.getString("Address"));
        order.setStatus(rs.getInt("Status"));
        order.setReasonDeny(rs.getString("ReasonDeny"));
        order.setNote(rs.getString("Note"));
        order.setDateCreate(rs.getDate("DateCreate"));
        order.setDatePackage(rs.getDate("DatePackage"));
        order.setDateReceived(rs.getDate("DateReceived"));
        order.setDatePunishment(rs.getDate("DatePunishment"));

        return order;
    }
}
