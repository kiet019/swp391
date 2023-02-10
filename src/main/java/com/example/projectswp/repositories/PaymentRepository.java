package com.example.projectswp.repositories;

import com.example.projectswp.model.Payment;
import com.example.projectswp.repositories.rowMapper.PaymentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepository {
    private static final PaymentRowMapper PAYMENT_ROW_MAPPER = new PaymentRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Payment> getPayments() {
        String sql = "";
        List<Payment> payment = jdbcTemplate.query(sql, PAYMENT_ROW_MAPPER);
        return payment.size() != 0? payment : null;
    }

    public Payment getPayment(int paymentId) {
        String sql = "";
        List<Payment> payment = jdbcTemplate.query(sql, PAYMENT_ROW_MAPPER, paymentId);
        return payment.size() != 0? payment.get(0) : null;
    }

    public boolean insertPayment(Payment payment) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }
    public int getLastPaymentID() {
        List<Payment> payments = getPayments();
        return payments.get( payments.size() - 1 ).getId();
    }

    public boolean updatePayment(int paymentId, Payment payment) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }

    public boolean deletePayment(int paymentID) {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql ,paymentID);
        return rowAffected > 0;
    }
}
