
package com.example.projectswp.repositories.rowMapper;


import com.example.projectswp.model.SubCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubCategoryRowMapper implements RowMapper<SubCategory> {

    @Override
    public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubCategory subCategory = new SubCategory();

        subCategory.setSubCategoryID(rs.getInt("Sub_CategoryID"));
        subCategory.setCategoryID(rs.getInt("CategoryID"));
        subCategory.setSubCategoryName(rs.getString("Sub_Category_Name"));
        subCategory.setStatus((rs.getBoolean("Sub_Category_Status")));
        return subCategory;
    }
}
