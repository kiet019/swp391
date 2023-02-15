package com.example.projectswp.controller;

import com.example.projectswp.model.SubCategory;
import com.example.projectswp.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/subcategory")
public class SubCategoryController {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping
    public ResponseEntity<List<SubCategory>> getSubCategories() {
        List<SubCategory> list = subCategoryRepository.getSubCategories();
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable int id) {
        SubCategory subCategory = subCategoryRepository.getSubCategory(id);
        return subCategory != null ? ResponseEntity.ok(subCategory) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
