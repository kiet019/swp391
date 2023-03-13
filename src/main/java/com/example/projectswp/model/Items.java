package com.example.projectswp.model;

import lombok.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Items {
    private int itemID;
    private String itemCode;
    private int userId;
    private int subCategoryId;
    private String itemTitle;
    private String itemDetailedDescription;
    private double itemMass;
    private boolean itemSize;
    private String itemQuanlity;
    private double itemEstimateValue;
    private double itemSalePrice;
    private int itemShareAmount;
    private boolean itemSponsoredOrderShippingFee;
    private Date stringDateTimeExpired;
    private String itemShippingAddress;
    private Date dateCreated;
    private Date dateUpdate;
    private boolean status;
    private boolean share;
    private String image;
}
