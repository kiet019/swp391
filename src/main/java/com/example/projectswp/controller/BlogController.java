package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/blog")

public class BlogController {

    /*
    * /api/blog/all (get)
    * /api/blog (get/put)
    * /api/useraccount/blog
    * /api/blog/create
    * /api/blog/accept
    * /api/blog/deny
     */


    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getBlogs() {
        List<Blog> blogs = blogRepository.getBlogs();
        return blogs != null? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    //TODO: fix path url
    @RequestMapping("/api/useraccount/blog")
    @GetMapping("")
    public ResponseEntity<List<Blog>> getBlog(int userID) {
        List<Blog> blogs = blogRepository.getBlogByUserId(userID);
        return blogs != null ? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("")

    @PutMapping("")
    public ResponseEntity<Blog> updateBlog(@PathVariable int blogId, @RequestBody Blog blog){
        boolean isUpdated = blogRepository.updateBlog(blogId, blog);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
        boolean result = blogRepository.insertBlog(blog);
        URI uri = URI.create("localhost:8080/api/blogs/" + blogRepository.getLastBlogId() );
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

//    @PatchMapping("/accept")
//
//    @PatchMapping("/deny")

}
