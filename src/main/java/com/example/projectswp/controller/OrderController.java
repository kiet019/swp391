package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.data_view_model.order.OrderGetVM;
import com.example.projectswp.data_view_model.order.OrderUpdateVM;
import com.example.projectswp.model.Items;
import com.example.projectswp.model.Order;
import com.example.projectswp.repositories.ItemsRepository;
import com.example.projectswp.repositories.OrderRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("reciever")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrderbyRecieverID(@ModelAttribute OrderGetVM orderGetVM) {
        try {
            int uid = Ultil.getUserId();
            List<Order> orderList = orderRepository.getOrderByRecieverId(uid, orderGetVM);
            if (orderList != null) {
                return ResponseEntity.ok(orderList);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("sharer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrderbyShareID(@ModelAttribute OrderGetVM orderGetVM) {
        try {
            int uid = Ultil.getUserId();
            List<Order> orderList = orderRepository.getOrderByShareId(uid, orderGetVM);
            if (orderList != null) {
                return ResponseEntity.ok(orderList);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("sharer/check")
    public ResponseEntity<?> name3(){
        return null;
    }
    @GetMapping("reciever/check")
    public ResponseEntity<?> name4(){
        return null;
    }
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrder(@RequestParam(name = "OrderId", required = true) int OrderId ){
        try {
            Order order = orderRepository.getOrderByOrderId(OrderId);
            if (order != null) {
                return ResponseEntity.ok(order);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateOrder(@RequestBody OrderUpdateVM orderUpdateVM){
        try {

            Order order = orderRepository.getOrderByOrderId(orderUpdateVM.getOrderId());
            if (order == null)
                return ResponseEntity.badRequest().body(ReturnMessage.create("order not found"));

            int uid = Ultil.getUserId();
            Items items = itemsRepository.getItem(order.getItemID());
            if (items != null || items.getUserId() != uid)
                return ResponseEntity.badRequest().body(ReturnMessage.create("not an Item owwner,deny change order"));

            boolean isUpdated = orderRepository.updateOrder(orderUpdateVM);
            if (isUpdated)
                return ResponseEntity.ok(ReturnMessage.create("success"));
            return ResponseEntity.badRequest().body(ReturnMessage.create("error at UpdateOrder"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
