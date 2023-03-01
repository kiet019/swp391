
package com.example.projectswp.repositories.rowMapper;


import com.example.projectswp.model.Blog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogRowMapper implements RowMapper<Blog> {

    @Override
    public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Blog blog = new Blog();
        blog.setBlogId(rs.getInt("BlogID"));
        blog.setBlogCategoryId(rs.getInt("Blog_CategoryID"));
        blog.setUserId(rs.getInt("UserID"));
        blog.setBlogTitle(rs.getString("Blog_Title"));
        blog.setBlogDescription(rs.getString("Blog_Description"));
        blog.setBlogContent(rs.getString("Blog_Content"));
        blog.setBlogDateCreate(rs.getDate("Blog_Date_Create"));
        blog.setBlogDateUpdate(rs.getDate("Blog_Date_Update"));
        blog.setBlogStatus(rs.getInt("Blog_Status"));
        return blog;
    }
}
