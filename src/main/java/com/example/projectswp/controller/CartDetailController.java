package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.cartdetail.CartDetailConfirmVM;
import com.example.projectswp.data_view_model.cartdetail.CartDetailGetVM;
import com.example.projectswp.model.Blog;
import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.CartDetailsRepository;
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
@RequestMapping("/api/cartdetail")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CartDetailController {
    @Autowired
    CartDetailsRepository cartDetailsRepository;
    @Autowired
    CartRepository cartRepository;
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CartDetails>> getCartDetails() {
        try {
            List<CartDetails> cartDetails = cartDetailsRepository.getCartDetails();
            return cartDetails != null ? ResponseEntity.ok(cartDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDetails> createCartDetail(@RequestBody CartDetails addCartDetails) {
        try {
            boolean result = cartDetailsRepository.addCartDetails(addCartDetails);
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateCartDetail(@RequestBody CartDetails cartDetails) {
        try {
            boolean result = false;
            if (cartDetailsRepository.getCartDetail(cartDetails.getCartDetailId()) != null) {
                result = cartDetailsRepository.updateCartDetail(cartDetails);
            }
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteItem(@RequestParam int cartDetailid){
        try {
            boolean result = false;
            if (cartRepository.getCarts(cartDetailsRepository.getCartDetail(cartDetailid).getCartId()).get(0).getUserID() == Ultil.getUserId()) {
                result = cartDetailsRepository.deleteCartDetail(cartDetailid);
            }
            return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/confirm")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> confirmCartDetail(@RequestBody CartDetailConfirmVM cartDetailConfirmVM) {
        boolean check = false;
        for (int cartDetailID : cartDetailConfirmVM.getListCartDetailID()) {
            check = cartDetailsRepository.cartDetailtoRequest(cartDetailConfirmVM.getNote(), cartDetailID, Ultil.getUserId());
            cartDetailsRepository.deleteCartDetail(cartDetailID);
            if (!check) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.ok().build();
    }
}
