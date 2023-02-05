package com.example.projectswp.repositories;

import com.example.projectswp.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();

        category.setId(rs.getInt("CategoryID"));
        category.setName(rs.getString("Category_Name"));
        category.setImage(rs.getString("Category_Image"));

        return category;
    }
}
