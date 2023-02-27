package com.example.projectswp.controller;

import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@RestController
@RequestMapping("/api/Category")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("GetAllCategory")
    public ResponseEntity<List<Category>> getCategories() {
        try {
            List<Category> list = categoryRepository.getCategories();
            return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("GetStatus")
    public ResponseEntity<List<Category>> getCategoriesByStatus(@RequestParam boolean categoryStatus) {
        try {
            List<Category> list = categoryRepository.getCategoryByStatus(categoryStatus);
            return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("SearchCategoryName")
    public ResponseEntity<List<Category>> getCategoriesByName(@RequestParam String categoryName) {
        try {
            List<Category> list = categoryRepository.getCategoryByName(categoryName);
            return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("CreateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> insertCategory(@RequestBody Category addCategory) {
        try {
            boolean result = categoryRepository.addCategory(addCategory);
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("UpdateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category updateCategory) {
        try {
            boolean result = false;
            if (categoryRepository.getCategory(updateCategory.getCategoryID()) != null) {
                result = categoryRepository.updateCategory(updateCategory.getCategoryID(), updateCategory);
            }
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("DeleteCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> deleteCategory(@RequestBody Category deleteCategory) {
        try {
            boolean result = false;
            if (categoryRepository.getCategory(deleteCategory.getCategoryID()) != null) {
                result = categoryRepository.deleteCategory(deleteCategory.getCategoryID());
            }
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
