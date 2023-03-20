package com.example.projectswp.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CartDetails {
    private int cartDetailId;
    private int cartId;
    private int itemId;
    private int itemQuantity;
}
