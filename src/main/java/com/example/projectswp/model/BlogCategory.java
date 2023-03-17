package com.example.projectswp.model;

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
    private List<Blog> blogList;
}
