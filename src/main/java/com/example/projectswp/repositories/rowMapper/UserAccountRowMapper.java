package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRowMapper implements RowMapper<UserAccount> {

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(rs.getInt("UserID"));
        userAccount.setCode(rs.getString("User_Code"));
        userAccount.setRoleId(rs.getInt("RoleID"));
        userAccount.setName(rs.getString("User_Name"));
        userAccount.setEmail(rs.getString("User_Gmail"));
        userAccount.setAddress(rs.getString("User_Address"));
        userAccount.setPhone(rs.getString("User_Phone"));
        userAccount.setSex(rs.getBoolean("User_Sex"));
        userAccount.setBirthDay(rs.getDate("User_Date_Of_Birth"));
        userAccount.setInformation(rs.getString("User_More_Information"));
        userAccount.setImg(rs.getString("User_Image"));
        userAccount.setStatus(rs.getBoolean("User_Status"));
        userAccount.setCreated(rs.getDate("User_Date_Create"));
        userAccount.setUpdate(rs.getDate("User_Date_Update"));
        return userAccount;
    }
}
