package com.example.projectswp.controller;

import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @GetMapping("/useraccount/cart")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Carts>> getCarts() {
        List<Carts> cart = cartRepository.getCarts();
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carts> addCart(@RequestBody Carts addCart) {
        boolean result = cartRepository.addCart(addCart);
        URI uri = URI.create("localhost:8080/api/carts/" + cartRepository.getLastCart().getCartID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
