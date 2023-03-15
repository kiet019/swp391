package com.example.projectswp.controller;

import com.example.projectswp.repositories.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/MyAddress")
public class AddressUserController {
    @Autowired
    UserAddressRepository userAddressRepository;

    @PostMapping("/NewAddress")
    public ResponseEntity<?> createAddress() {
        return null;
    }
    @GetMapping("/MyAddress")
    public ResponseEntity<?> getAddress() {
        return null;
    }
    @DeleteMapping("/DeleteAddress")
    public ResponseEntity<?> deleteAddress() {
        return null;
    }
}
