package com.example.projectswp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAddress {
    private int id;
    private String address;
    private int userID;
}
