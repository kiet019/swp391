package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.CartDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cartdetail")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CartDetailController {
    @Autowired
    CartDetailsRepository cartDetailsRepository;

    @GetMapping("/{cartDetailID}")
    public ResponseEntity<CartDetails> getCartDetail(@PathVariable int cartDetailID) {
        CartDetails cartDetails = cartDetailsRepository.getCartDetail(cartDetailID);
        return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/api/useraccount/cartdetail/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CartDetails>> getCartDetails() {
        List<CartDetails> cartDetails = cartDetailsRepository.getCartDetails();
        return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/cartdetail/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CartDetails> createCartDetail(@RequestBody CartDetails addCartDetails) {
        boolean result = cartDetailsRepository.addCartDetails(addCartDetails);
        URI uri = URI.create("localhost:8080/api/cartdetail/" + cartDetailsRepository.getLastCartDetails().getCartDetailsID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PatchMapping("/cartdetail/accept")
    public ResponseEntity<CartDetails> cartDetailAccept(@RequestBody int cartDetailID){
        boolean isUpdated = cartDetailsRepository.acceptStatus(cartDetailID);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/cartdetail/cancel")
    public ResponseEntity<CartDetails> cartDetailCancel(@RequestBody int cartDetailID){
        boolean isUpdated = cartDetailsRepository.cancelStatus(cartDetailID);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/cartdetail/confirm")
    public ResponseEntity<CartDetails> cartDetailConfirm(@RequestBody int cartDetailID){
        boolean isUpdated = cartDetailsRepository.confirmStatus(cartDetailID);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Items> deleteItem(@RequestBody int cartDetailId){
        boolean result = cartDetailsRepository.deleteCartDetail(cartDetailId);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }


}
