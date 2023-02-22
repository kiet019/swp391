package com.example.projectswp.repositories;

import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.rowMapper.UserAccountRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAccountRepository {

    private static final UserAccountRowMapper USER_ACCOUNT_ROW_MAPPER = new UserAccountRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserAccount getUserAccount(String code) {
        String sql = "select * from UserAccounts where User_Code = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, code);
        return userAccounts.size() != 0 ? userAccounts.get(0) : null;
    }

    public boolean addUserAccount(UserRecord userRecord) {
        String sql = "insert [dbo].[UserAccounts]([User_Code],[RoleID],[User_Name],[User_Gmail],[User_Address],[User_Date_Of_Birth],[User_Image],[User_Status],[User_Date_Create])\n" +
                "values ( ? , ?, ?, ?, ?, ?, ?, ?, ?)";

        int rowAffected = jdbcTemplate.update(sql, userRecord.getUid(), 2, userRecord.getEmail(), userRecord.getEmail(), " ", " ", " ", true, Ultil.getCurrentDate());
        return rowAffected > 0;
    }

    public int getUserAccountId(String code) {
        String sql = "select * from UserAccounts where User_Code = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, code);
        return userAccounts.size() != 0 ? userAccounts.get(0).getId() : 0;
    }

    public int getUserAccountRole(String code) {
        String sql = "select * from UserAccounts where User_Code = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, code);
        return userAccounts.size() != 0 ? userAccounts.get(0).getRoleId() : 0;
    }
}
