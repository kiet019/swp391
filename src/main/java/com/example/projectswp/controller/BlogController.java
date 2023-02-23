package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.BlogRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BlogController {

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

    //TODO: test this function
    @GetMapping("")
    public ResponseEntity<List<Blog>> getBlog(@RequestParam int categoryId, @RequestParam int userId) {
        List<Blog> blogs = blogRepository.getBlogByUserId(userId);
        return blogs != null ? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/blog/test")
    public String test(@RequestParam int categoryId, @RequestParam int userId) {
        return categoryId + " | " + userId;
    }

    @PutMapping("")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog){
        boolean isUpdated = blogRepository.updateBlog(blog);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/blog/create")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
        blog.setUserId(Ultil.getUserId());
        boolean result = blogRepository.insertBlog(blog);
        URI uri = URI.create("localhost:8080/api/blogs/" + blogRepository.getLastBlogId() ); //TODO: rewrite uri
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PatchMapping("/blog/accept")
    public ResponseEntity<Blog> blogAccept(@RequestBody int blogId){
        boolean isUpdated = blogRepository.updateStatusToTrue(blogId);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/blog/deny")
    public ResponseEntity<Blog> blogDeny(@RequestBody int blogId){
        boolean isDeleted = blogRepository.deleteBlog(blogId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
