package com.example.projectswp.repositories;

import com.example.projectswp.model.Blog;
import com.example.projectswp.repositories.rowMapper.BlogRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class BlogRepository {
    private static final BlogRowMapper BLOG_ROW_MAPPER = new BlogRowMapper();
    private static final boolean BLOG_STATUS = false;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Blog> getBlogs() {
        String sql = "Select * from Blogs";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER);
        return blogs.size() != 0? blogs: null;
    }
    public List<Blog> getBlogs(final int userID) {
        String sql = "select * from Blogs where UserID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, userID);
        return blogs.size() != 0? blogs: null;
    }

    public Blog getBlog(final int blogID){
        String sql = "select * from Blogs where BlogID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, blogID);
        return blogs.size() != 0? blogs.get(0): null;
    }
    public boolean insertBlog(Blog blog) {
        if(isExist(blog.getBlogId()) || blog == null){
            return false;
        }
        String sql = "INSERT INTO dbo.Blogs(Blog_CategoryID, UserID, Blog_Title, Blog_Description, Blog_Content, Blog_Date_Create, Blog_Status) values (?, ?, ?, ?, ?, ?, ?)";
        Date currentDate = Ultil.getCurrentDate();
        int rowAffected = jdbcTemplate.update(sql, blog.getBlogCategoryId(), blog.getUserId(), blog.getBlogTitle(),
                blog.getBlogDescription(), blog.getBlogContent(), Ultil.getCurrentDate(), BLOG_STATUS);
        return rowAffected > 0;
    }
    public int getLastBlogId(){
        List<Blog> blogs = getBlogs();
        return (blogs != null) ? blogs.get(blogs.size() - 1).getBlogId() : -1;
    }

    public boolean updateBlog(Blog blog){
        if(blog == null || !isExist(blog.getBlogId()))
            return false;

        updateBlogData(blog);
        String sql = "UPDATE dbo.Blogs set Blog_CategoryID = ?, Blog_Title = ?, Blog_Description = ?, Blog_Content = ?, Blog_Date_Update = ? WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, blog.getBlogCategoryId(),
                blog.getBlogTitle(), blog.getBlogDescription(), blog.getBlogContent(), Ultil.getCurrentDate(), blog.getBlogId());
        return rowAffected > 0;
    }
    private void updateBlogData(Blog blog){
        Blog oldbBlog = getBlog(blog.getBlogId());
        if(oldbBlog == null)
            return;

        if(blog.getBlogCategoryId() == 0){
            blog.setBlogCategoryId(oldbBlog.getBlogCategoryId());
        }

        if(blog.getBlogTitle() == null || blog.getBlogTitle().trim().length() == 0){
            blog.setBlogTitle(oldbBlog.getBlogTitle());
        }

        if(blog.getBlogDescription() == null || blog.getBlogDescription().trim().length() == 0){
            blog.setBlogDescription(oldbBlog.getBlogDescription());
        }

        if(blog.getBlogContent() == null || blog.getBlogContent().trim().length() == 0){
            blog.setBlogContent(oldbBlog.getBlogContent());
        }
    }
    public boolean deleteBlog(int id){
        if(!isExist(id)){
            return false;
        }
        String sql = "DELETE dbo.Blogs WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, id);
        return rowAffected > 0;
    }

    public List<Blog> getBlogByUserId(final int userID){
        String sql = "select * from Blogs where UserID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, userID);
        return blogs.size() != 0? blogs: null;
    }

    public boolean updateStatusToTrue(final int blogId) {
        String sql = "UPDATE dbo.Blogs set Blog_Status = 1 WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, blogId);
        return rowAffected > 0;
    }

    private boolean isExist(int id){
        return getBlog(id) != null;
    }
}
