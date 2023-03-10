package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.BlogCategoryGetVM;
import com.example.projectswp.data_view_model.blogcategory.CreateBlogCategoryVM;
import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.repositories.BlogCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    public ResponseEntity<String> getBlogCategory(@ModelAttribute BlogCategoryGetVM blogCategoryGetVM) {
        BlogCategory blogCategory = blogCategoryRepository.getBlogCategory(blogCategoryGetVM.getBlogCategoryId());
        return blogCategory != null ? ResponseEntity.ok(blogCategory.getBlogCategoryName()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create")
    public ResponseEntity<ReturnMessage> createBlogCategory(@RequestBody CreateBlogCategoryVM blogCategoryNameVM) {
        if(blogCategoryRepository.isExistName(blogCategoryNameVM.getBlogCategoryName())) {
            return ResponseEntity.badRequest().body(ReturnMessage.create("this name already existed"));
        }else{
            boolean isCreated = blogCategoryRepository.insertBlogCategory(blogCategoryNameVM.getBlogCategoryName());
            return isCreated ? ResponseEntity.ok().body(ReturnMessage.create("success")) : ResponseEntity.badRequest().body(ReturnMessage.create("error at create Blog Category"));
        }
    }

}
