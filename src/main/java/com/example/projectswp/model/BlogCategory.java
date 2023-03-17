package com.example.projectswp.model;

import com.example.projectswp.data_view_model.blog.BlogForBlogCategory;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogCategory {
    private int blogCategoryId;
    private String blogCategoryName;
    private List<BlogForBlogCategory> blogList;
}
