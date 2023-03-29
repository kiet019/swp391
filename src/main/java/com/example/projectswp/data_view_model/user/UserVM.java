package com.example.projectswp.data_view_model.user;

import com.example.projectswp.model.UserAccount;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserVM {
    private int userId;
    private String userName;
    private String userGmail;
    private String userImage;
    private int reputation;
    private boolean userStatus;
    private Date userDateCreate;

    public static UserVM create(UserAccount userAccount) {
        UserVM userVM = new UserVM();
        userVM.setUserId(userAccount.getUserId());
        userVM.setUserName(userAccount.getUserName());
        userVM.setUserGmail(userAccount.getUserEmail());
        userVM.setUserImage(userAccount.getUserImage());
        userVM.setReputation(userAccount.getReputation());
        userVM.setUserStatus(userAccount.isStatus());
        userVM.setUserDateCreate(userAccount.getCreated());

        return  userVM;
    }
}
