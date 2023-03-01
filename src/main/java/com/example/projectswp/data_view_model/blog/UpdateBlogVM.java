package com.example.projectswp.data_view_model.blog;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateBlogVM {
    private int blogId;
    private int blogCategoryId;
    private String blogTitle;
    private String blogDescription;
    private String blogContent;

}
