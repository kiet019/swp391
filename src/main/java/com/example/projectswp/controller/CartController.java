package com.example.projectswp.controller;
import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Items;
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
    CartRepository cartRepository = new CartRepository();

    @GetMapping("/{cartID}")
    public ResponseEntity<Carts> getCart(@PathVariable int id) {
        Carts cart = cartRepository.getCart(id);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("")
    public ResponseEntity<List<Carts>> getCarts() {
        List<Carts> cart = cartRepository.getCarts();
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("")
    public ResponseEntity<Carts> insertCarts(@RequestBody Carts addCarts) {
        boolean result = cartRepository.addCart(addCarts);
        URI uri = URI.create("localhost:8080/api/carts/" + cartRepository.getLastCart().getCartID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PutMapping("")
    public ResponseEntity<Items> updateCarts(@RequestBody Carts updateCart) {
        boolean result = false;
        if (cartRepository.getCart(updateCart.getCartID()) != null) {
            result = cartRepository.updateCart(updateCart);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
