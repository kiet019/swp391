package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.CartDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cart-details")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CartDetailController {
    @Autowired
    CartDetailsRepository cartDetailsRepository;

    @GetMapping("/{cartDetailID}")
    public ResponseEntity<CartDetails> getCartDetail(@PathVariable int cartDetailID) {
        CartDetails cartDetails = cartDetailsRepository.getCartDetail(cartDetailID);
        return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("")
    public ResponseEntity<List<CartDetails>> getCartDetails() {
        List<CartDetails> cartDetails = cartDetailsRepository.getCartDetails();
        return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<CartDetails> createCartDetail(@RequestBody CartDetails addCartDetails) {
        boolean result = cartDetailsRepository.addCartDetails(addCartDetails);
        URI uri = URI.create("localhost:8080/api/cart-details/" + cartDetailsRepository.getLastCartDetails().getCartDetailsID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/{cartDetailID}")
    public ResponseEntity<CartDetails> updateCartDetail(@PathVariable int cartDetailID, @RequestBody CartDetails cartDetail) {
        boolean result = cartDetailsRepository.updateCartDetail(cartDetailID, cartDetail);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/{cartDetailID}")
    public ResponseEntity<CartDetails> deleteCartDetail(@PathVariable int cartDetailID){
        boolean result = cartDetailsRepository.deleteCartDetail(cartDetailID);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
