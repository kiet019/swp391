package com.example.projectswp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private int id;
    private String code;
    private int roleId;
    private String name;
    private String email;
    private String address;
    private String phone;
    private boolean sex;
    private Date birthDay;
    private String information;
    private String img;
    private boolean status;
    private Date created;
    private Date update;
}
