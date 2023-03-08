package com.example.projectswp.data_view_model.comment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentCreateVM {
    private int itemId;
    private int blogId;
    private String commentContent;
}
