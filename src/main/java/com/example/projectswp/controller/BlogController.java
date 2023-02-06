package com.example.projectswp.controller;

import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    // blogs/blogid; delete by id;

    @Autowired
    BlogRepository blogRepository = new BlogRepository();

    @GetMapping("")
    public ResponseEntity<List<Blog>> getBlogs() {
        List<Blog> blogs = blogRepository.getBlogs();
        return blogs != null? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("users/{userid}/blog")
    public ResponseEntity<List<Blog>> getBlogs(@PathVariable int userID) {
        List<Blog> blogs = blogRepository.getBlogs(userID);
        return blogs != null? ResponseEntity.ok(blogs) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/{blogID}")
    public ResponseEntity<Blog> getBlog(@PathVariable int blogID) {
        Blog blog = blogRepository.getBlog(blogID);
        return blog != null? ResponseEntity.ok(blog) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("")
    public void createBlog(){

    }

    @GetMapping("/test")
    public String test() {
        try{
            Blog blog = blogRepository.getBlog(1);
            return blog != null? "success" : "fail";
        }catch(Exception e){
            System.out.println(e);
        }finally {
            return "fail";
        }

    }
}
