package com.example.projectswp.model;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Request {
    private int requestID;
    private int userID;
    private int itemQuantity;
    private int itemID;
    private String address;
    private String note;
    private Date dateCreate;
    private int status;
    private Date dateChangeStatus;
}
