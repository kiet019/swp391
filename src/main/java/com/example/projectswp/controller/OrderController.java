package com.example.projectswp.controller;

import com.example.projectswp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/useraccount/order/reciever")
    public ResponseEntity<?> name1(){
        return null;
    }
    @GetMapping("/useraccount/order/sharer")
    public ResponseEntity<?> name2(){
        return null;
    }
    @GetMapping("useraccount/order/sharer/check")
    public ResponseEntity<?> name3(){
        return null;
    }
    @GetMapping("useraccount/order/reciever/check")
    public ResponseEntity<?> name4(){
        return null;
    }
    @GetMapping("/order")
    public ResponseEntity<?> name5(){
        return null;
    }
    @PutMapping("/order")
    public ResponseEntity<?> name6(){
        return null;
    }

}
