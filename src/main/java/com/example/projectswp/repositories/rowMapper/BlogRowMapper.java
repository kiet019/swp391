
package com.example.projectswp.repositories.rowMapper;


import com.example.projectswp.model.Blog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogRowMapper implements RowMapper<Blog> {

    @Override
    public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Blog blog = new Blog();
        blog.setID(rs.getInt("BlogID"));
        blog.setCategoryID(rs.getInt("Blog_CategoryID"));
        blog.setUserID(rs.getInt("UserID"));
        blog.setTitle(rs.getString("Blog_Title"));
        blog.setDescription(rs.getString("Blog_Description"));
        blog.setContent(rs.getString("Blog_Content"));
        blog.setDateCreated(rs.getDate("Blog_Date_Create"));
        blog.setDateUpdated(rs.getDate("Blog_Date_Update"));
        return blog;
    }
}
