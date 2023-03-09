package com.example.projectswp.model;

import lombok.*;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    private int commentID;
    private int userID;
    private int blogID;
    private int itemID;
    private Date dateCreate;
    private Date dateUpdate;
    private String commentContent;
    private List<Reply> listReply;
}
