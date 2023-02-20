package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.model.Order;
import com.example.projectswp.model.OrderDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setId(rs.getInt("OrderID"));
        order.setUserID(rs.getInt("UserID"));
        order.setCode(rs.getString("Order_Code"));
        order.setPaymentID(rs.getInt("PaymentID"));
        order.setStatus(rs.getInt("Order_Status"));
        order.setDateCreate(rs.getDate("Order_Date_Create"));
        order.setDateUpdate(rs.getDate("Order_Date_Update"));
        order.setAddress(rs.getString("Order_Address"));

        return order;
    }
}
