package com.example.projectswp.repositories;

import com.example.projectswp.model.Order;
import com.example.projectswp.repositories.rowMapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private static final OrderRowMapper ORDER_ROW_MAPPER = new OrderRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getOrders() {
        String sql = "";
        List<Order> order = jdbcTemplate.query(sql, ORDER_ROW_MAPPER);
        return order.size() != 0? order : null;
    }

    public Order getOrder(int orderId) {
        String sql = "";
        List<Order> order = jdbcTemplate.query(sql, ORDER_ROW_MAPPER, orderId);
        return order.size() != 0? order.get(0) : null;
    }

    public boolean insertOrder(Order order) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }
    public int getLastOrderID() {
        List<Order> orders = getOrders();
        return orders.get( orders.size() - 1 ).getId();
    }

    public boolean updateOrder(int orderId, Order order) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }

    public boolean deleteOrder(int orderID) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql ,orderID);
        return rowAffected > 0;
    }
}
