package com.example.projectswp.repositories;

import com.example.projectswp.model.Blog;
import com.example.projectswp.rowmapper.BlogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepository {
    //get post put delete
    private static final BlogRowMapper BLOG_ROW_MAPPER = new BlogRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Blog> getBlogs() {
        String sql = "Select * from Blogs";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER);
        return blogs.size() != 0? blogs: null;
    }
    public List<Blog> getBlogs(int userID) {
        String sql = "select * from Blogs where UserID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, userID);
        return blogs.size() != 0? blogs: null;
    }

    public Blog getBlog(int blogID){
        String sql = "select * from Blogs where BlogID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, blogID);
        return blogs.size() != 0? blogs.get(0): null;
    }
    public boolean insertBlog(Blog blog) {
        String sql = "";


        return false;
    }
}
