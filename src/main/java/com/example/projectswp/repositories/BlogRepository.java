package com.example.projectswp.repositories;

import com.example.projectswp.data_view_model.blog.BlogDenyVM;
import com.example.projectswp.data_view_model.blog.BlogForBlogCategory;
import com.example.projectswp.data_view_model.blog.CreateBlogVM;
import com.example.projectswp.data_view_model.blog.UpdateBlogVM;
import com.example.projectswp.model.Blog;
import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.model.Comment;
import com.example.projectswp.repositories.rowMapper.BlogRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BlogRepository {
    private static final BlogRowMapper BLOG_ROW_MAPPER = new BlogRowMapper();
    private static final int BLOG_STATUS = 0;
    private static final int DENY_STATUS = 2;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CommentRepository commentRepository;

    public List<Blog> getBlogs() {
        String sql = "SELECT * FROM Blogs where order by [Blog_Date_Create] DESC";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER);
        addCommentToBlogList(blogs);
        return blogs.size() != 0 ? blogs : null;
    }

    public Blog getBlog(int blogID) {
        String sql = "SELECT * FROM Blogs WHERE BlogID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, blogID);
        addCommentToBlogList(blogs);
        return blogs.size() != 0 ? blogs.get(0) : null;
    }
    public boolean insertBlog(int uid, CreateBlogVM blogVM) {
        String sql = "INSERT INTO dbo.Blogs(Blog_CategoryID, UserID, Blog_Title, Image, Blog_Description, Blog_Content, Blog_Date_Create, Blog_Status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql, blogVM.getBlogCategoryId(), uid, blogVM.getBlogTitle(), blogVM.getImage(),
                blogVM.getBlogDescription(), blogVM.getBlogContent(), LocalDateTime.now(), BLOG_STATUS);
        return rowAffected > 0;
    }

    public boolean updateBlog(int uid, UpdateBlogVM blogVM) {
        Blog blog = getBlog(uid);
        if (blog == null || blog.getBlogStatus() == 3)
            return false;

        String sql = "UPDATE dbo.Blogs " +
                "SET Blog_CategoryID = ?, Blog_Title = ?, Image = ?, Blog_Description = ?, Blog_Content = ?, Blog_Date_Update = ?, Blog_Status = ? " +
                "WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, blogVM.getBlogCategoryId(), blogVM.getBlogTitle(), blogVM.getImage(),
                blogVM.getBlogDescription(), blogVM.getBlogContent(), Ultil.getCurrentDate(), BLOG_STATUS, uid);
        return rowAffected > 0;
    }
    public boolean denyBlog(BlogDenyVM blogDenyVM) {
        if(getBlog(blogDenyVM.getBlogId()) == null)
            return false;
        String sql = "UPDATE dbo.Blogs SET Blog_Status = ?, Reason_Deny = ? WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, DENY_STATUS, blogDenyVM.getReason(),blogDenyVM.getBlogId());
        return rowAffected > 0;
    }

    public List<Blog> getBlogByUserId(int userID) {
        String sql = "SELECT * FROM Blogs WHERE UserID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, userID);
        addCommentToBlogList(blogs);
        return blogs.size() != 0 ? blogs: null;
    }

    public List<Blog> getBlogByCategoryId(int blogCategoryId) {
        String sql = "SELECT * FROM Blogs WHERE Blog_CategoryID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, blogCategoryId);
        addCommentToBlogList(blogs);
        return blogs.size() != 0 ? blogs: null;
    }

    public boolean updateStatus(int blogId, int status) {
        if(getBlog(blogId) == null)
            return false;
        String sql = "UPDATE dbo.Blogs SET Blog_Status = ? WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, status, blogId);
        return rowAffected > 0;
    }

    private void addCommentToBlogList(List<Blog> blogs) {
        if (blogs == null)
            return;
        for (Blog blog: blogs) {
            int blogId = blog.getBlogId();
            List<Comment> commentList = commentRepository.getCommentByBlogId(blogId);
            blog.setListComment(commentList);
        }
    }
    public List<BlogForBlogCategory> blogListForBlogCategory(int blogcategoryId) {
        List<Blog> blogList = getBlogByCategoryId(blogcategoryId);
        List<BlogForBlogCategory> blogListForCategory = new ArrayList<>();
        if (blogList != null) {
            for (Blog blog : blogList) {
                BlogForBlogCategory blogOfBlogCategory = BlogForBlogCategory.create(blog);
                blogListForCategory.add(blogOfBlogCategory);
            }
        }
        return blogListForCategory;
    }
}
