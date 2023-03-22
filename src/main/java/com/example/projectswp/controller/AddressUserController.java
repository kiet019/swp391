package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.model.UserAddress;
import com.example.projectswp.repositories.UserAddressRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressUserController {
    @Autowired
    UserAddressRepository userAddressRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAddress(@RequestBody String location) {
        int uid = Ultil.getUserId();
        boolean result = userAddressRepository.createAddress(uid, location);
        return result ? ResponseEntity.ok(ReturnMessage.create("đã thêm địa chỉ mới")) : ResponseEntity.badRequest().build();
    }
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAddress() {
        int uid = Ultil.getUserId();
        List<UserAddress> list = userAddressRepository.getUserAddress(uid);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteAddress(@RequestBody String location) {
        int uid = Ultil.getUserId();
        boolean result = userAddressRepository.deleteAddress(uid, location);
        return result ? ResponseEntity.ok(ReturnMessage.create("đã xóa địa chỉ")) : ResponseEntity.badRequest().build();
    }
}
