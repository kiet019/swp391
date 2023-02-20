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
        String sql = "SELECT * FROM dbo.Payments";
        List<Payment> payments = jdbcTemplate.query(sql, PAYMENT_ROW_MAPPER);
        return payments.size() != 0 ? payments : null;
    }

    public Payment getPayment(int paymentId) {
        String sql = "SELECT * FROM dbo.Payments WHERE PaymentID = ?";
        List<Payment> payment = jdbcTemplate.query(sql, PAYMENT_ROW_MAPPER, paymentId);
        return payment.size() != 0 ? payment.get(0) : null;
    }

    public boolean insertPayment(Payment payment) {
        if (payment == null)
            return false;
        String sql = "INSERT INTO dbo.Payments(Payment_Name, Payment_Description) VALUES (?, ?)";
        int rowAffected = jdbcTemplate.update(sql, payment.getName(), payment.getDescription());
        return rowAffected > 0;
    }

    public int getLastPaymentID() {
        List<Payment> payments = getPayments();
        return payments.get((payments.size() - 1)).getId();
    }

    public boolean updatePayment(int paymentId, Payment payment) {
        Payment newPayment = updatePaymentObject(paymentId, payment);
        if (newPayment == null)
            return false;

        String sql = "UPDATE dbo.Payments set Payment_Name = ?, Payment_Description = ? where PaymentID = ?";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }

    public Payment updatePaymentObject(int id, Payment payment) {
        Payment oldPayment = getPayment(id);
        if (oldPayment == null)
            return null;

        if (payment.getName() == null || payment.getName().trim().length() == 0) {
            payment.setName(oldPayment.getName());
        }

        if (payment.getDescription() == null || payment.getDescription().trim().length() == 0) {
            payment.setDescription(oldPayment.getDescription());
        }
        return payment;
    }

    public boolean deletePayment(int paymentID) {
        String sql = "DELETE dbo.Payments WHERE PaymentID = ?";
        int rowAffected = jdbcTemplate.update(sql, paymentID);
        return rowAffected > 0;
    }
}
