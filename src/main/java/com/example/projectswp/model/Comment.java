package com.example.projectswp.model;

import lombok.*;

import java.util.Date;


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
}
