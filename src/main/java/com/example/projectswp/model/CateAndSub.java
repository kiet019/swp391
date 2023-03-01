package com.example.projectswp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CateAndSub {

    private int categoryID;
    private String categoryName;
    private String categoryImage;
    private List<SubCategory> subCategoryList;

}
