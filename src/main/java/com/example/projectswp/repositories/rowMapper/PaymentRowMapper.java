package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.model.OrderDetail;
import com.example.projectswp.model.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();

        payment.setId(rs.getInt("PaymentID"));
        payment.setName(rs.getString("Payment_Name"));
        payment.setDescription(rs.getString("Payment_Description"));

        return payment;
    }
}
