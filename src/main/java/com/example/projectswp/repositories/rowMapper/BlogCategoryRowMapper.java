package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.BlogCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogCategoryRowMapper implements RowMapper<BlogCategory> {
    @Override
    public BlogCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setBlogCategoryId(rs.getInt("Blog_CategoryID"));
        blogCategory.setBlogCateName(rs.getString("Blog_Category_Name"));
        return blogCategory;
    }
}
