package com.example.projectswp.data_view_model.blog;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateBlogVM {
    private int blogCategoryId;
    private String image;
    private String blogTitle;
    private String blogDescription;
    private String blogContent;
}
