package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int id;
    private int userID;
    private String code;
    private int paymentID;
    private int status;
    private Date dateCreate;
    private Date dateUpdate;
    private String address;
}
