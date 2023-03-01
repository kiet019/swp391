package com.example.projectswp.data_view_model.user;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateUserVM {
    private String userName;
    private String userImage;
    private String userAddress;
    private String userPhone;
    private boolean userSex;
    private Date userDateOfBirth;
    private String userMoreInformation;
}
