
package com.example.projectswp.repositories.rowMapper;


import com.example.projectswp.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();

        category.setCategoryID(rs.getInt("CategoryID"));
        category.setCategoryName(rs.getString("Category_Name"));
        category.setCategoryImage(rs.getString("Category_Image"));
        category.setCategoryStatus(rs.getBoolean("Category_Status"));

        return category;
    }
}
