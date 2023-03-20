package com.example.projectswp.data_view_model.blog;

import com.example.projectswp.model.Blog;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogForBlogCategory {
    private int blogId;
    private String blogTitle;
    private String image;
    private String blogDescription;
    private Date blogDateCreate;
    private int blogStatus;
    public static BlogForBlogCategory create(Blog blog) {
        if (blog == null)
            return null;

        BlogForBlogCategory blogCategory = new BlogForBlogCategory();
        blogCategory.setBlogId(blog.getBlogId());
        blogCategory.setBlogTitle(blog.getBlogTitle());
        blogCategory.setImage(blog.getImage());
        blogCategory.setBlogDescription(blog.getBlogDescription());
        blogCategory.setBlogDateCreate(blog.getBlogDateCreate());
        blogCategory.setBlogStatus(blog.getBlogStatus());
        return blogCategory;
    }
}
