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
    public ResponseEntity<?> getBlogCategories() {
        try {
            List<BlogCategory> blogCategories = blogCategoryRepository.getBlogCategories();
            return ResponseEntity.ok(blogCategories);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReturnMessage.create(ex.getMessage()));
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getBlogCategory(@RequestParam(name = "BlogCategoryId",required = true) int blogCategoryId) {
        try {
            BlogCategory blogCategory = blogCategoryRepository.getBlogCategory(blogCategoryId);
            return ResponseEntity.ok(blogCategory);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReturnMessage.create(ex.getMessage()));
        }

    }

    @PostMapping("/create")
    public ResponseEntity<ReturnMessage> createBlogCategory(@RequestBody CreateBlogCategoryVM blogCategoryNameVM) {
        try {

            if (blogCategoryRepository.isExistName(blogCategoryNameVM.getBlogCategoryName())) {
                return ResponseEntity.badRequest().body(ReturnMessage.create("this name already existed"));
            } else {
                boolean isCreated = blogCategoryRepository.insertBlogCategory(blogCategoryNameVM.getBlogCategoryName());
                return isCreated ? ResponseEntity.ok().body(ReturnMessage.create("created success")) : ResponseEntity.badRequest().body(ReturnMessage.create("error at create Blog Category"));
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReturnMessage.create(ex.getMessage()));
        }
    }
}
