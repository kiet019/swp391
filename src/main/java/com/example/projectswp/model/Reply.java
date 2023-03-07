package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reply {
    private int relyID;
    private String commentID;
    private int userID;
    private Date dateCreate;
    private Date dateUpdate;
    private String relyContent;
}
