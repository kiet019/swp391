package com.example.projectswp.controller;

import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/carts")

public class CartController {
    @Autowired
    CartRepository cartRepository;
    @GetMapping("")
    public ResponseEntity<List<Carts>> getCarts() {
        List<Carts> cart = cartRepository.getCarts();
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/{cartID}")
    public ResponseEntity<Carts> getCart(@PathVariable int cartID) {
        Carts cart = cartRepository.getCart(cartID);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<Carts> addCart(@RequestBody Carts addCart) {
        boolean result = cartRepository.addCart(addCart);
        URI uri = URI.create("localhost:8080/api/carts/" + cartRepository.getLastCart().getCartID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/{cartID}")
    public ResponseEntity<Carts> updateCart(@PathVariable int cartID, @RequestBody Carts carts) {
        boolean isUpdated = cartRepository.updateCart(cartID, carts);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{cartID}")
    public ResponseEntity<Carts> deleteCart(@PathVariable int id){
        boolean result = cartRepository.deleteCart(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
