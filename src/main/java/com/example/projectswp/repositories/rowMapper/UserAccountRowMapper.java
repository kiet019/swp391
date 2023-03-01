package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRowMapper implements RowMapper<UserAccount> {

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(rs.getInt("UserID"));
        userAccount.setUserCode(rs.getString("User_Code"));
        userAccount.setRoleId(rs.getInt("RoleID"));
        userAccount.setUserName(rs.getString("User_Name"));
        userAccount.setUserEmail(rs.getString("User_Gmail"));
        userAccount.setUserAddress(rs.getString("User_Address"));
        userAccount.setUserPhone(rs.getString("User_Phone"));
        userAccount.setUserSex(rs.getBoolean("User_Sex"));
        userAccount.setUserDateOfBirth(rs.getDate("User_Date_Of_Birth"));
        userAccount.setUserMoreInformation(rs.getString("User_More_Information"));
        userAccount.setUserImage(rs.getString("User_Image"));
        userAccount.setStatus(rs.getBoolean("User_Status"));
        userAccount.setCreated(rs.getDate("User_Date_Create"));
        userAccount.setUpdate(rs.getDate("User_Date_Update"));
        return userAccount;
    }
}
