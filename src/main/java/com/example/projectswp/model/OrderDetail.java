package com.example.projectswp.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private int id;
    private int orderID;
    private int itemID;
    private int status;
    private int quantity;
    private BigDecimal price;
    private Date dateUpdate;
    private String shareAddress;
}
