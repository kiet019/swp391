package com.example.projectswp.repositories;

import com.example.projectswp.data_view_model.order.OrderGetVM;
import com.example.projectswp.data_view_model.order.OrderUpdateVM;
import com.example.projectswp.model.Order;
import com.example.projectswp.model.Request;
import com.example.projectswp.repositories.rowMapper.OrderRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private static  final int ORDER_CREATE_STATUS = 0;
    private static final OrderRowMapper ORDER_ROW_MAPPER = new OrderRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Order getOrderByOrderId(int orderId) {
        String sql = "SELECT * FROM dbo.Orders WHERE OrderID = ?";
        List<Order> orderList = jdbcTemplate.query(sql,ORDER_ROW_MAPPER, orderId);
        if (orderList != null && orderList.size() > 0)
            return  orderList.get(0);
        return null;
    }
    public boolean updateOrder(OrderUpdateVM orderUpdateVM) {
        if(orderUpdateVM.getStatus() == 1 || orderUpdateVM.getStatus() == 2) {
            String sql = "UPDATE dbo.Orders SET Status = ? WHERE OrderID = ?";
            int rowAffected = jdbcTemplate.update(sql, orderUpdateVM.getStatus(), orderUpdateVM.getOrderId());
            return rowAffected > 0;
        }
        return false;
    }
    public List<Order> getOrderByRecieverId(int uid, OrderGetVM orderGetVM){
        if(orderGetVM == null || uid == 0)
            return null;

        String sql = "";
        List<Order> orders = jdbcTemplate.query(sql, ORDER_ROW_MAPPER);
        return orders;

    }
    public List<Order> getOrderByShareId(int uid, OrderGetVM orderGetVM){
        if(orderGetVM == null || uid == 0)
            return null;
        List<Order> orders;
        if(orderGetVM.getOrderStatus() == null) {
            String sql = "";
            orders = jdbcTemplate.query(sql, ORDER_ROW_MAPPER);
        } else {
            String sql = "";
            orders = jdbcTemplate.query(sql, ORDER_ROW_MAPPER);
        }

        return orders;
    }



    public boolean createOrder(Request request) {
        if (request == null)
            return false;
        String sql = "INSERT INTO dbo.Orders(UserID, ItemID, Quanlity, Address, Note, Status, DateCreate) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql, request.getUserID(), request.getItemID(),
                request.getItemQuantity(), request.getAddress(), request.getNote(), ORDER_CREATE_STATUS, Ultil.getCurrentDate());
        return rowAffected > 0;
    }
}
