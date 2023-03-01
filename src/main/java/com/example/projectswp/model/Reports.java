package com.example.projectswp.model;

import lombok.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reports {
    private int reportID;
    private int itemID;
    private Date reportDateCreate;
    private Date reportDateUpdate;
    private boolean status;
    private String content;
    private String image;
}
