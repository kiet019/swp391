package com.example.projectswp.controller;

import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.CartDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cart-details")

public class CartDetailController {
    @Autowired
    CartDetailsRepository cartDetailsRepository = new CartDetailsRepository();
    @GetMapping("/{cartDetailsID}")
    public ResponseEntity<CartDetails> getCartDetail(@PathVariable int id) {
        CartDetails cartDetails = cartDetailsRepository.getCartDetail(id);
        return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("")
    public ResponseEntity<List<CartDetails>> getCartDetails() {
        List<CartDetails> cartDetails = cartDetailsRepository.getCartDetails();
        return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("")
    public ResponseEntity<CartDetails> insertCartDetails(@RequestBody CartDetails addCartDetails) {
        boolean result = cartDetailsRepository.addCartDetails(addCartDetails);
        URI uri = URI.create("localhost:8080/api/cart-details/" + cartDetailsRepository.getLastCartDetails().getCartDetailsID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PutMapping("")
    public ResponseEntity<CartDetails> updateCartDetails(@RequestBody CartDetails updateCartDetails) {
        boolean result = false;
        if (cartDetailsRepository.getCartDetail(updateCartDetails.getCartDetailsID()) != null) {
            result = cartDetailsRepository.updateCart(updateCartDetails);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
