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

    private int id;
    private int categoryID;
    private String name;

    private boolean status;
}
