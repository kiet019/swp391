package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CartDetails {
    private int cartDetailsID;
    private int cartID;
    private int itemID;
    private Date cartDetailDateCreate;
    private Date cartDetailDateUpdate;
    private int cartDetailItemQuantity;
    private int cartStatus;
}
