package com.example.projectswp.controller;

import com.example.projectswp.model.SubCategory;
import com.example.projectswp.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/SubCategory")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SubCategoryController {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping("/GetAllSubCategory")
    public ResponseEntity<List<SubCategory>> getSubCategories() {
        List<SubCategory> list = subCategoryRepository.getSubCategories();
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/SearchSubCategoryName/{name}")
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable String name) {
        SubCategory subCategory = subCategoryRepository.getSubCategoryByName(name);
        return subCategory != null ? ResponseEntity.ok(subCategory) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

//    @PostMapping("CreateSubCategory")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<SubCategory> insertSubCategory(@RequestBody SubCategory insertSubcategory) {
//        boolean result = false;
//        if (subCategoryRepository.getSubCategoryByName(insertSubcategory))
//    }
}
