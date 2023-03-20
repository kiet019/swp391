package com.example.projectswp.controller;

import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.CartRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @GetMapping("/useraccount/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Carts>> getCarts() {
        List<Carts> cart = cartRepository.getCarts();
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/cart/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> addCart() {
        int userID= Ultil.getUserId();
        boolean result = false;
        if (cartRepository.getCarts() == null) {
            result = cartRepository.addCart(userID);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PatchMapping("/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateCartAddress(@RequestBody Carts carts) {
        boolean result = false;
        if (cartRepository.getCarts() != null) {
            result = cartRepository.updateCartAddress(carts.getAddress(), Ultil.getUserId());
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
