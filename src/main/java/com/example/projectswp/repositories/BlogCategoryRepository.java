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
        String sql = "select * from BlogCategories where Blog_CategoryID = ?";
        List<BlogCategory> categories = jdbcTemplate.query(sql, BLOG_CATEGORY_ROW_MAPPER, blogCategoryId);
        return categories.size() != 0? categories.get(0) : null;
    }

    public boolean insertBlogCategory(BlogCategory blogCategory) {
        String sql = "INSERT INTO dbo.BlogCategories(Blog_Category_Name) VALUES(?)";
        int rowAffected = jdbcTemplate.update(sql, BLOG_CATEGORY_ROW_MAPPER, blogCategory.getName());
        return rowAffected > 0;
    }
    public int getLastBlogCategoryID() {
        List<BlogCategory> blogCategories = getBlogCategories();
        return blogCategories.get( blogCategories.size()-1 ).getId();
    }

    public boolean updateBlogCategory(int blogCategoryID, String blogCategoryName) {
        String sql = "UPDATE dbo.BlogCategories SET Blog_Category_Name = ? WHERE Blog_CategoryID = ?";
        int rowAffected = jdbcTemplate.update(sql, BLOG_CATEGORY_ROW_MAPPER, blogCategoryName, blogCategoryID);
        return rowAffected > 0;
    }

    public boolean deleteBlogCategory(int blogCategoryId) {
        String sql = "DELETE dbo.BlogCategories WHERE Blog_CategoryID = ?";
        int rowAffected = jdbcTemplate.update(sql, BLOG_CATEGORY_ROW_MAPPER, blogCategoryId);
        return rowAffected > 0;
    }
}
