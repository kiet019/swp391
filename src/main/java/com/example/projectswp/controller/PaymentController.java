package com.example.projectswp.controller;

import com.example.projectswp.model.Payment;
import com.example.projectswp.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/payments")
@CrossOrigin
public class PaymentController {
    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("")
    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentRepository.getPayments();
        return payments != null? ResponseEntity.ok(payments) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{paymentID}")
    public ResponseEntity<Payment> getPayment(@PathVariable int paymentID) {
        Payment payment = paymentRepository.getPayment(paymentID);
        return payment != null? ResponseEntity.ok(payment) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        boolean result = paymentRepository.insertPayment(payment);
        URI uri = URI.create("localhost:8080/api/payments/" + paymentRepository.getLastPaymentID() );
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int paymentId, @RequestBody Payment payment){
        boolean isUpdated = paymentRepository.updatePayment(paymentId, payment);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Payment> deletePayment(@PathVariable int paymentId){
        boolean isDeleted = paymentRepository.deletePayment(paymentId);
        return isDeleted ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
