package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.model.OrderDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {

    @Override
    public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setId(rs.getInt("Order_DetailID"));
        orderDetail.setOrderID(rs.getInt("OrderID"));
        orderDetail.setItemID(rs.getInt("ItemID"));
        orderDetail.setStatus(rs.getInt("Order_Detail_Status"));
        orderDetail.setQuantity(rs.getInt("Order_Detail_Quantity"));
        orderDetail.setPrice(rs.getBigDecimal("Order_Detail_Price"));
        orderDetail.setDateUpdate(rs.getDate("Order_Detail_Date_Update"));
        orderDetail.setShareAddress(rs.getString("Order_Detail_Share_Address"));

        return orderDetail;
    }
}
