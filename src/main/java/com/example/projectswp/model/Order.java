package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int orderID;
    private int userID;
    private int itemID;
    private int quanlity;
    private String address;
    private int status;
    private  String reasonDeny;
    private String note;
    private Date dateCreate;
    private Date datePackage;
    private Date dateReceived;
    private Date datePunishment;

}
