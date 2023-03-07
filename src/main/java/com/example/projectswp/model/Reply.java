package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reply {
    private int replyId;
    private int commentId;
    private int userID;
    private Date dateCreate;
    private Date dateUpdate;
    private String replyContent;
}
