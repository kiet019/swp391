package com.example.projectswp.repositories;

import com.example.projectswp.model.Order;
import com.example.projectswp.repositories.rowMapper.OrderRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private static final OrderRowMapper ORDER_ROW_MAPPER = new OrderRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void test() {
        System.out.println("not" + "test");
    }

    public List<Order> getOrders() {
        String sql = "SELECT * FROM Orders";
        List<Order> orders = jdbcTemplate.query(sql, ORDER_ROW_MAPPER);
        return orders.size() != 0 ? orders : null;
    }

    public Order getOrder(int orderId) {
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        List<Order> order = jdbcTemplate.query(sql, ORDER_ROW_MAPPER, orderId);
        return order.size() != 0 ? order.get(0) : null;
    }

    public boolean insertOrder(Order order) {
        String sql = "INSERT INTO dbo.Orders(UserID, Order_Code, PaymentID, Order_Status, Order_Date_Create, Order_Address) VALUES (?, ?, ?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql, order.getUserID(), order.getCode(), order.getPaymentID(), order.getStatus(), Ultil.getCurrentDate(), order.getAddress());
        return rowAffected > 0;
    }

    public int getLastOrderID() {
        List<Order> orders = getOrders();
        return orders.get(orders.size() - 1).getId();
    }

    public boolean updateOrder(final int orderId, Order order) {
        Order oldOrder = getOrder(orderId);
        if (oldOrder == null)
            return false;
        updateOrderObject(oldOrder, order);
        String sql = "UPDATE dbo.Orders SET UserID = ?, Order_Code = ?, PaymentID = ?, Order_Status = ?, Order_Date_Update = ?, Order_Address = ? WHERE OrderID = ?";
        int rowAffected = jdbcTemplate.update(sql, order.getUserID(), order.getCode(), order.getPaymentID(), order.getStatus(), Ultil.getCurrentDate(), order.getAddress(), orderId);
        return rowAffected > 0;
    }

    private void updateOrderObject(Order oldOrder, Order order) {
        if (order.getUserID() == 0)
            order.setUserID(order.getUserID());

        if (order.getCode() == null || order.getCode().trim().length() == 0)
            order.setCode(oldOrder.getCode());

        if (order.getPaymentID() == 0)
            order.setPaymentID(oldOrder.getPaymentID());

        if (order.getStatus() == 0)
            order.setStatus(oldOrder.getStatus());

        if (order.getAddress() == null || order.getAddress().trim().length() == 0)
            order.setAddress(oldOrder.getAddress());

    }

    public boolean deleteOrder(int orderID) {
        String sql = "DELETE dbo.Orders WHERE OrderID = ?";
        int rowAffected = jdbcTemplate.update(sql, orderID);
        return rowAffected > 0;
    }
}
