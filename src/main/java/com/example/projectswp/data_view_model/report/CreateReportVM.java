package com.example.projectswp.data_view_model.report;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateReportVM {
    private int itemID;
    private int userID;
    private int status;
    private String content;
    private String image;
}
