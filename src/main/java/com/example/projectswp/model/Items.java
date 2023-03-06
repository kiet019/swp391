package com.example.projectswp.model;

import lombok.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Items {
    private int ID;
    private String itemCode;
    private int userID;
    private int subCategoryID;
    private String itemTitle;
    private String itemDetailedDescription;
    private double mass;
    private boolean size;
    private String quanlity;
    private double value;
    private double price;
    private int amount;
    private boolean sponsoredOrderShippingFee;
    private Date time;
    private String address;
    private Date dateCreated;
    private Date dateUpdate;
    private boolean status;
    private boolean share;
    private String imageID;
}
