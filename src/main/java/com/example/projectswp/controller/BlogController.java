package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blog.*;
import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.BlogRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BlogController {
    private static final int ACCEPT_STATUS = 1;

    private static final int DELETE_STATUS = 3;
    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/blog/all")
    public ResponseEntity<List<Blog>> getBlogs() {
        List<Blog> blogs = blogRepository.getBlogs();
        return blogs != null? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/useraccount/blog")
    public ResponseEntity<List<Blog>> getBlog() {
        int userID = Ultil.getUserId();
        List<Blog> blogs = blogRepository.getBlogByUserId(userID);
        return blogs != null ? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/blog")
    public ResponseEntity<?> getBlog(@ModelAttribute BlogGetVM blogGetVM) {
        List<Blog> blogs;
        if(blogGetVM.getCategoryId() != 0){
            blogs = blogRepository.getBlogByCategoryId(blogGetVM.getCategoryId());
            return blogs != null ? ResponseEntity.ok(blogs) : ResponseEntity.notFound().build();
        } else if(blogGetVM.getUserId() != 0){
            blogs = blogRepository.getBlogByUserId(blogGetVM.getUserId()) ;
            return blogs != null ? ResponseEntity.ok(blogs) : ResponseEntity.notFound().build();
        } else if(blogGetVM.getBlogId() != 0){
            Blog blog = blogRepository.getBlog(blogGetVM.getBlogId());
            return blog != null ? ResponseEntity.ok(blog) : ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().body(ReturnMessage.create("fail at get blogs"));
    }

    @PutMapping("/blog")
    public ResponseEntity<ReturnMessage> updateBlog(@RequestBody UpdateBlogVM blogVM){
        int uid = Ultil.getUserId();
        boolean isUpdated = blogRepository.updateBlog(uid, blogVM);
        return isUpdated ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/blog/create")
    public ResponseEntity<ReturnMessage> createBlog(@RequestBody CreateBlogVM blogVM){
        int uid = Ultil.getUserId();
        boolean result = blogRepository.insertBlog(uid, blogVM);
        return result ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PatchMapping("/blog/accept")
    public ResponseEntity<?> blogAccept(@RequestBody BlogIdVM blogIdVM){
        boolean isUpdated = blogRepository.updateStatus(blogIdVM.getBlogId(), ACCEPT_STATUS);
        return isUpdated ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/blog/deny")
    public ResponseEntity<ReturnMessage> blogDeny(@RequestBody BlogDenyVM blogDenyVM){
        boolean isDeleted = blogRepository.denyBlog(blogDenyVM);
        return isDeleted ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/blog/delete")
    public ResponseEntity<ReturnMessage> deleteBlog(@RequestBody BlogIdVM blogIdVM){
        boolean isDeleted = blogRepository.updateStatus(blogIdVM.getBlogId(), DELETE_STATUS);
        return isDeleted ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
