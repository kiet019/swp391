package com.example.projectswp.controller;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.repositories.BlogCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

@RequestMapping("api/blogcategory")
public class BlogCategoryController {
    @Autowired
    BlogCategoryRepository blogCategoryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<BlogCategory>> getBlogCategories() {
        List<BlogCategory> BlogCategories = blogCategoryRepository.getBlogCategories();
        return BlogCategories != null? ResponseEntity.ok(BlogCategories) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("")
    public ResponseEntity<BlogCategory> getBlogCategory(@RequestBody int BlogCategoryId) {
        BlogCategory blogCategory = blogCategoryRepository.getBlogCategory(BlogCategoryId);
        return blogCategory != null ? ResponseEntity.ok(blogCategory) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create")
    public ResponseEntity<BlogCategory> createBlogCategory(@RequestBody String blogCateName) {
        boolean isCreated = blogCategoryRepository.insertBlogCategory(blogCateName);
        URI uri = URI.create("localhost:8080/api/blog-categories/" + blogCategoryRepository.getLastBlogCategoryID() );
        return isCreated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

}
