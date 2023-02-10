package com.example.projectswp.repositories;

import com.example.projectswp.model.OrderDetail;
import com.example.projectswp.repositories.rowMapper.OrderDetailRowMapper;
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
        String sql = "";
        List<OrderDetail> orderDetail = jdbcTemplate.query(sql, ORDER_DETAIL_ROW_MAPPER);
        return orderDetail.size() != 0? orderDetail : null;
    }

    public OrderDetail getOrderDetail(int orderDetailId) {
        String sql = "";
        List<OrderDetail> orderDetail = jdbcTemplate.query(sql, ORDER_DETAIL_ROW_MAPPER, orderDetailId);
        return orderDetail.size() != 0? orderDetail.get(0) : null;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }
    public int getLastOrderDetailID() {
        List<OrderDetail> orderDetails = getOrderDetails();
        return orderDetails.get( orderDetails.size() - 1 ).getId();
    }

    public boolean updateOrderDetail(int orderDetailId, OrderDetail orderDetail) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }

    public boolean deleteOrderDetail(int orderDetailID) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql ,orderDetailID);
        return rowAffected > 0;
    }
}
