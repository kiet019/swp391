package com.example.projectswp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private int orderDetailID;
    private int orderID;
    private int itemID;
    private int quanlity;
    private String address;
    private int status;
    private boolean sponsoredOrderShippingFee;
    private String reasonDeny;
    private String note;
}
