package com.example.projectswp.repositories;

import com.example.projectswp.data_view_model.blog.BlogDenyVM;
import com.example.projectswp.data_view_model.blog.BlogForBlogCategory;
import com.example.projectswp.data_view_model.blog.CreateBlogVM;
import com.example.projectswp.data_view_model.blog.UpdateBlogVM;
import com.example.projectswp.model.Blog;
import com.example.projectswp.model.BlogCategory;
import com.example.projectswp.repositories.rowMapper.BlogRowMapper;
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
        String sql = "Select * from Blogs";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER);
        addCommentToBlogList(blogs);
        return blogs.size() != 0? blogs: null;
    }

    public Blog getBlog(int blogID){
        String sql = "select * from Blogs where BlogID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, blogID);
        addCommentToBlogList(blogs);
        return blogs.size() != 0? blogs.get(0): null;
    }
    public boolean insertBlog(int uid, CreateBlogVM blogVM) {
        String sql = "INSERT INTO dbo.Blogs(Blog_CategoryID, UserID, Blog_Title, Blog_Description, Blog_Content, Blog_Date_Create, Blog_Status) values (?, ?, ?, ?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql, blogVM.getBlogCategoryId(), uid, blogVM.getBlogTitle(),
                blogVM.getBlogDescription(), blogVM.getBlogContent(), LocalDateTime.now(), BLOG_STATUS);
        return rowAffected > 0;
    }

    public boolean updateBlog(int uid, UpdateBlogVM blogVM){
        Blog blog = getBlog(uid);
        if(blog == null || blog.getBlogStatus() == 3)
            return false;

        String sql = "UPDATE dbo.Blogs set Blog_CategoryID = ?, Blog_Title = ?, Blog_Description = ?, Blog_Content = ?, Blog_Date_Update = ?, Blog_Status = ? WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, blogVM.getBlogCategoryId(),
                blogVM.getBlogTitle(), blogVM.getBlogDescription(), blogVM.getBlogContent(), LocalDateTime.now(), BLOG_STATUS, uid);
        return rowAffected > 0;
    }
    public boolean denyBlog(BlogDenyVM blogDenyVM) {
        if(getBlog(blogDenyVM.getBlogId()) == null)
            return false;

        String sql = "UPDATE dbo.Blogs set Blog_Status = ?, Reason_Deny = ? WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, DENY_STATUS, blogDenyVM.getReason(),blogDenyVM.getBlogId());
        return rowAffected > 0;
    }
    public void updateBlogData(Blog blog){
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

    public List<Blog> getBlogByUserId(int userID){
        String sql = "select * from Blogs where UserID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, userID);
        addCommentToBlogList(blogs);
        return blogs.size() != 0? blogs: null;
    }

    public List<Blog> getBlogByCategoryId(int blogcategoryId){
        String sql = "select * from Blogs where Blog_CategoryID = ?";
        List<Blog> blogs = jdbcTemplate.query(sql,BLOG_ROW_MAPPER, blogcategoryId);
        addCommentToBlogList(blogs);
        return blogs.size() != 0 ? blogs: null;
    }

    public boolean updateStatus(int blogId, int status) {
        if(getBlog(blogId) == null)
            return false;

        String sql = "UPDATE dbo.Blogs set Blog_Status = ? WHERE BlogID = ?";
        int rowAffected = jdbcTemplate.update(sql, status, blogId);
        return rowAffected > 0;
    }

    private void addCommentToBlogList(List<Blog> blogs) {
        if(blogs == null)
            return;

        for(Blog blog: blogs) {
            blog.setListComment(commentRepository.getCommentByBlogId(blog.getBlogId()));
        }
    }
    public List<BlogForBlogCategory> blogListForBlogCategory(int blogcategoryId) {
        List<Blog> blogList = getBlogByCategoryId(blogcategoryId);
        List<BlogForBlogCategory> blogListForCategory = new ArrayList<>();
        if (blogList != null) {
            for(Blog blog : blogList) {
                blogListForCategory.add(BlogForBlogCategory.create(blog));
            }
        }
        return blogListForCategory;
    }

}
