package com.example.projectswp.controller;

import com.example.projectswp.model.CateAndSub;
import com.example.projectswp.repositories.CateAndSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category-sub")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CateAndSubController {
    @Autowired
    CateAndSubRepository cateAndSubRepository;
    @GetMapping("")
    public ResponseEntity<List<CateAndSub>> getCateAndSub() {
        List<CateAndSub> list = cateAndSubRepository.getCateAndSubs();
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
