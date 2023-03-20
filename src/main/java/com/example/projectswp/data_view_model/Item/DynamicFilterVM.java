package com.example.projectswp.data_view_model.Item;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DynamicFilterVM {
    private int categoryID;
    private String titleName;
    private String location;
    private float minPrice;
    private float maxPrice;
    private double maxUsable;
    private double minUsable;
}
