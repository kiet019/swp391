package com.example.projectswp.data_view_model.blog;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogGetVM {

    private int categoryId;
    private int userId;
    private int BlogId;
    private int pageNumber;
    private int pageSize;


}
