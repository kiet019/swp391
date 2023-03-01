package com.example.projectswp.data_view_model.report;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateReportVM {
    private int reportID;
    private String content;
    private String image;
}
