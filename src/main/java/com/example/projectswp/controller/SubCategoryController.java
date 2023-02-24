package com.example.projectswp.controller;

import com.example.projectswp.model.SubCategory;
import com.example.projectswp.repositories.CateAndSubRepository;
import com.example.projectswp.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path="/api/SubCategory")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SubCategoryController {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping("/GetAllSubCategory")
    public ResponseEntity<List<SubCategory>> getSubCategories(@RequestParam int categoryID) {
        List<SubCategory> list = subCategoryRepository.getSubCategoriesByCategory(categoryID);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/SearchSubCategoryName")
    public ResponseEntity<List<SubCategory>> getSubCategory(@RequestParam String subCategoryName) {
        List<SubCategory> list = subCategoryRepository.getSubCategoryByName(subCategoryName);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/createSubCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SubCategory>> insertSubCategory(@RequestBody SubCategory addSubCategory) {
        boolean result = subCategoryRepository.addSubCategory(addSubCategory);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/updateSubCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory) {
        boolean result = false;
        if (subCategoryRepository.getSubCategory(subCategory.getSubCategoryID()) != null) {
            result = subCategoryRepository.updateSubCategory(subCategory);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/DeleteCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubCategory> deleteSubCategory(@RequestBody SubCategory subCategory) {
        boolean result = false;
        if (subCategoryRepository.getSubCategory(subCategory.getSubCategoryID()) != null) {
            result = subCategoryRepository.deleteSubCategory(subCategory);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
