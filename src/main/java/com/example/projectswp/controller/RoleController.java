package com.example.projectswp.controller;

import com.example.projectswp.model.Role;
import com.example.projectswp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository = new RoleRepository();

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        Role role = roleRepository.getRole(id);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
