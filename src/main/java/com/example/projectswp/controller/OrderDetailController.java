package com.example.projectswp.controller;

import com.example.projectswp.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ordercontroller")
public class OrderDetailController {
    @Autowired
    OrderDetailRepository orderDetailRepository;


}
