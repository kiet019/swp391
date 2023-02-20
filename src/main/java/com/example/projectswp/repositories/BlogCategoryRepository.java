package com.example.projectswp.repositories;

import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.repositories.rowMapper.BlogCategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogCategoryRepository {
    private static final BlogCategoryRowMapper BLOG_CATEGORY_ROW_MAPPER = new BlogCategoryRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<BlogCategory> getBlogCategories() {
        String sql = "select * from BlogCategories";
        List<BlogCategory> categories = jdbcTemplate.query(sql, BLOG_CATEGORY_ROW_MAPPER);
        return categories.size() != 0? categories : null;
    }

    public BlogCategory getBlogCategory(int blogCategoryId) {
        String sql = "SELECT * from BlogCategories WHERE Blog_CategoryID = ?";
        List<BlogCategory> categories = jdbcTemplate.query(sql, BLOG_CATEGORY_ROW_MAPPER, blogCategoryId);
        return categories.size() != 0? categories.get(0) : null;
    }

    public boolean insertBlogCategory(String blogCateName) {
        String sql = "INSERT INTO dbo.BlogCategories(Blog_Category_Name) VALUES(?)";
        int rowAffected = jdbcTemplate.update(sql, blogCateName);
        return rowAffected > 0;
    }
    public int getLastBlogCategoryID() {
        List<BlogCategory> blogCategories = getBlogCategories();
        return blogCategories.get( blogCategories.size()-1 ).getId();
    }

    public boolean updateBlogCategory(int blogCategoryID, BlogCategory blogCategory) {
        String sql = "UPDATE dbo.BlogCategories SET Blog_Category_Name = ? WHERE Blog_CategoryID = ?";
        int rowAffected = jdbcTemplate.update(sql, blogCategory.getName(), blogCategoryID);
        return rowAffected > 0;
    }

    public boolean deleteBlogCategory(int blogCategoryId) {
        String sql = "DELETE dbo.BlogCategories WHERE Blog_CategoryID = ?";
        int rowAffected = jdbcTemplate.update(sql ,blogCategoryId);
        return rowAffected > 0;
    }
}
