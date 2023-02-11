package com.example.projectswp.repositories;

import com.example.projectswp.model.OrderDetail;
import com.example.projectswp.repositories.rowMapper.OrderDetailRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailRepository {
    private static final OrderDetailRowMapper ORDER_DETAIL_ROW_MAPPER = new OrderDetailRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderDetail> getOrderDetails() {
        String sql = "SELECT * FROM dbo.OrderDetails";
        List<OrderDetail> orderDetails = jdbcTemplate.query(sql, ORDER_DETAIL_ROW_MAPPER);
        return orderDetails.size() != 0 ? orderDetails : null;
    }

    public OrderDetail getOrderDetail(int orderDetailId) {
        String sql = "SELECT * FROM dbo.OrderDetails WHERE Order_DetailID = ?";
        List<OrderDetail> orderDetail = jdbcTemplate.query(sql, ORDER_DETAIL_ROW_MAPPER, orderDetailId);
        return orderDetail.size() != 0 ? orderDetail.get(0) : null;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO dbo.OrderDetails(OrderID, ItemID, Order_Detail_Status, Order_Detail_Quantity, Order_Detail_Price, Order_Detail_Date_Update ,Order_Detail_Share_Address) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql, orderDetail.getOrderID(), orderDetail.getItemID(), orderDetail.getStatus(), orderDetail.getQuantity(), orderDetail.getPrice(), Ultil.getCurrentDate(), orderDetail.getShareAddress());
        return rowAffected > 0;
    }

    public int getLastOrderDetailID() {
        List<OrderDetail> orderDetails = getOrderDetails();
        return orderDetails.get(orderDetails.size() - 1).getId();
    }

    public boolean updateOrderDetail(int orderDetailId, OrderDetail orderDetail) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }

    public boolean deleteOrderDetail(int orderDetailID) {
        String sql = "DELETE dbo.OrderDetails where Order_DetailID = ?";
        int rowAffected = jdbcTemplate.update(sql, orderDetailID);
        return rowAffected > 0;
    }
}
