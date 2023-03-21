package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blog.*;
import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.BlogRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/blog")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BlogController {
    private static final int ACCEPT_STATUS = 1;
    private static final int DELETE_STATUS = 3;
    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getBlogs(@ModelAttribute BlogPage blogPage) {
        try {
            List<Blog> blogs = blogRepository.getBlogs(1);
            BlogListVM blogListVM = BlogListVM.create(blogs, blogPage.getPageNumber(), blogPage.getPageSize());
            return ResponseEntity.ok(blogListVM);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/token")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getBlog(@ModelAttribute BlogPage blogPage) {
        try {
            int userID = Ultil.getUserId();
            List<Blog> blogs = blogRepository.getBlogByUserId(userID);
            BlogListVM blogListVM = BlogListVM.create(blogs, blogPage.getPageNumber(), blogPage.getPageSize());
            return ResponseEntity.ok(blogListVM);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getBlog(@ModelAttribute BlogGetVM blogGetVM) {
        try {
            List<Blog> blogs;
            if (blogGetVM.getCategoryId() != 0) {
                blogs = blogRepository.getBlogByCategoryId(blogGetVM.getCategoryId());
            } else if (blogGetVM.getUserId() != 0) {
                blogs = blogRepository.getBlogByUserId(blogGetVM.getUserId()) ;
            } else if (blogGetVM.getBlogId() != 0) {
                Blog blog = blogRepository.getBlog(blogGetVM.getBlogId());
                return blog != null ? ResponseEntity.ok(blog) : ResponseEntity.notFound().build();
            } else {
                throw new IllegalArgumentException("Invalid parameters: categoryId, userId, or blogId must be provided.");
            }

            BlogListVM blogListVM = BlogListVM.create(blogs, blogGetVM.getPageNumber(), blogGetVM.getPageSize());
            return ResponseEntity.ok(blogListVM);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ReturnMessage.create(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReturnMessage> updateBlog(@RequestBody UpdateBlogVM blogVM) {
        try {
            int uid = Ultil.getUserId();
            boolean isUpdated = blogRepository.updateBlog(uid, blogVM);
            return isUpdated ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReturnMessage.create(ex.getMessage()));
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReturnMessage> createBlog(@RequestBody CreateBlogVM createBlogVM) {
        try {
            int uid = Ultil.getUserId();
            boolean success = blogRepository.insertBlog(uid, createBlogVM);

            if (success) {
                return ResponseEntity.ok(ReturnMessage.create("Blog created successfully."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ReturnMessage.create("Failed to create blog."));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReturnMessage.create(ex.getMessage()));
        }
    }

    @PatchMapping("/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReturnMessage> blogAccept(@RequestBody BlogIdVM blogIdVM) {
        try {
            boolean isUpdated = blogRepository.updateStatus(blogIdVM.getBlogId(), ACCEPT_STATUS);
            return isUpdated ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReturnMessage.create(ex.getMessage()));
        }
    }

    @PatchMapping("/deny")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReturnMessage> blogDeny(@RequestBody BlogDenyVM blogDenyVM) {
        try {
            boolean isDeleted = blogRepository.denyBlog(blogDenyVM);
            return isDeleted ? ResponseEntity.ok(ReturnMessage.create("success")) : ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/delete")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ReturnMessage> deleteBlog(@RequestBody BlogIdVM blogIdVM) {
        try {
            boolean isDeleted = blogRepository.updateStatus(blogIdVM.getBlogId(), DELETE_STATUS);
            return isDeleted ? ResponseEntity.ok(ReturnMessage.create("Blog deleted successfully.")) : ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
