package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.model.UserAddress;
import com.example.projectswp.repositories.UserAddressRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/MyAddress")
public class AddressUserController {
    @Autowired
    UserAddressRepository userAddressRepository;

    @PostMapping("/NewAddress")
    public ResponseEntity<?> createAddress(@RequestBody String location) {
        int uid = Ultil.getUserId();
        boolean result = userAddressRepository.createAddress(uid, location);
        return result ? ResponseEntity.ok(ReturnMessage.create("đã thêm địa chỉ mới")) : ResponseEntity.badRequest().build();
    }
    @GetMapping("/MyAddress")
    public ResponseEntity<?> getAddress() {
        int uid = Ultil.getUserId();
        UserAddress userAddress = userAddressRepository.getUserAddress(uid);
        return userAddress != null ? ResponseEntity.ok(userAddress) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/DeleteAddress")
    public ResponseEntity<?> deleteAddress(@RequestBody String location) {
        int uid = Ultil.getUserId();
        boolean result = userAddressRepository.deleteAddress(uid, location);
        return result ? ResponseEntity.ok(ReturnMessage.create("đã xóa địa chỉ")) : ResponseEntity.badRequest().build();
    }
}
