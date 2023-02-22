package com.example.projectswp.controller;

import com.example.projectswp.model.CateAndSub;
import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/Category")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Category category = categoryRepository.getCategory(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/GetAllCategory")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> list = categoryRepository.getCategories();
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/GetStatus/{status}")
    public ResponseEntity<List<Category>> getCategoriesByStatus(@PathVariable boolean status) {
        List<Category> list = categoryRepository.getCategoriesByStatus(status);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/SearchCategoryName/{name}")
    public ResponseEntity<List<Category>> getCategoriesByName(@PathVariable String name) {
        List<Category> list = categoryRepository.getCategoriesByName(name);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/CreateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> insertCategory(@RequestBody Category addCategory) {
        boolean result = categoryRepository.addCategory(addCategory);
        URI uri = URI.create("" + categoryRepository.getLastCategory().getId());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/UpdateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category updateCategory) {
        boolean result = false;
        if (categoryRepository.getCategory(updateCategory.getId()) != null) {
             result = categoryRepository.updateCategory(updateCategory.getId(), updateCategory);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/DeleteCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> DeleteCategory(@RequestBody Category deleteCategory) {
        boolean result = false;
        if (categoryRepository.getCategory(deleteCategory.getId()) != null) {
            result = categoryRepository.deleteCategory(deleteCategory.getId());
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
