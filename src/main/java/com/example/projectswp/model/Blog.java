package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Blog {
    private int ID;
    private int categoryID;
    private int userID;
    private String title;
    private String description;
    private String content;
    private Date dateCreated;
    private Date dateUpdated;
}
