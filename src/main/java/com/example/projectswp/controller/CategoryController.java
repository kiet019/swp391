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
        List<Category> list = categoryRepository.getCategories();
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("GetStatus/{status}")
    public ResponseEntity<List<Category>> getCategoriesByStatus(@PathVariable boolean status) {
        List<Category> list = categoryRepository.getCategoryByStatus(status);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("SearchCategoryName/{name}")
    public ResponseEntity<List<Category>> getCategoriesByStatus(@PathVariable String name) {
        List<Category> list = categoryRepository.getCategoryByName(name);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("CreateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> insertCategory(@RequestBody Category addCategory) {
        List<Category> list = categoryRepository.getCategoryByName(addCategory.getName());
        if (list != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        boolean result = categoryRepository.addCategory(addCategory);
        URI uri = URI.create("localhost:8080/api/category/" + categoryRepository.getLastCategory().getId());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("UpdateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category updateCategory) {
        boolean result = false;
        if (categoryRepository.getCategory(updateCategory.getId()) != null) {
             result = categoryRepository.updateCategory(updateCategory.getId(), updateCategory);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("DeleteCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> deleteCategory(@RequestBody Category deleteCategory) {
        boolean result = false;
        if (categoryRepository.getCategory(deleteCategory.getId()) != null) {
            result = categoryRepository.deleteCategory(deleteCategory.getId());
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
