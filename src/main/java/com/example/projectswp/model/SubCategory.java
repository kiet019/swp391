package com.example.projectswp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    private int subCategoryID;
    private int categoryID;
    private String subCategoryName;
    private boolean status;
}
