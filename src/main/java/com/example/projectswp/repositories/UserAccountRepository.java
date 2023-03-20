package com.example.projectswp.repositories;

import com.example.projectswp.data_view_model.user.UpdateUserVM;
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

    public List<UserAccount> getUserAccounts(int skip, int getNumber){
        String sql = "select * from UserAccounts ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, skip, getNumber);
        return userAccounts.size() != 0 ? userAccounts : null;
    }
    public UserAccount getUserAccount(String code) {
        String sql = "select * from UserAccounts where User_Code = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, code);
        return userAccounts.size() != 0 ? userAccounts.get(0) : null;
    }

    public UserAccount getUserAccountById(int id) {
        String sql = "select * from UserAccounts where UserID = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, id);
        return userAccounts.size() != 0 ? userAccounts.get(0) : null;
    }

    public String getUserAddress(int id) {
        String sql = "select * from UserAccounts where UserID = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, id);
        return userAccounts.size() != 0 ? userAccounts.get(0).getUserAddress() : null;
    }

    public boolean addUserAccount(UserAccount userAccount, UserRecord userRecord, int reputaion) {
        String sql = "insert [dbo].[UserAccounts]([User_Code],[RoleID],[User_Name],[User_Gmail],[User_Address], [User_Phone], [User_Sex],[User_Date_Of_Birth],[User_Image],[User_Status],[User_Date_Create], [Reputation])\n" +
                "values ( ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int rowAffected = jdbcTemplate.update(sql, userRecord.getUid(), 2, userRecord.getEmail(), userRecord.getEmail(), userAccount.getUserAddress(), userAccount.getUserPhone(), userAccount.isUserSex(),userAccount.getUserDateOfBirth(), " ", true, Ultil.getCurrentDate(), reputaion);
        return rowAffected > 0;
    }

    public int getUserAccountId(String code) {
        String sql = "select * from UserAccounts where User_Code = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, code);
        return userAccounts.size() != 0 ? userAccounts.get(0).getUserId() : 0;
    }

    public int getUserAccountRole(String code) {
        String sql = "select * from UserAccounts where User_Code = ?";
        List<UserAccount> userAccounts = jdbcTemplate.query(sql, USER_ACCOUNT_ROW_MAPPER, code);
        return userAccounts.size() != 0 ? userAccounts.get(0).getRoleId() : 0;
    }
    public boolean updateUserStatus(int userId, boolean status) {
        if(!isExistUser(userId))
            return false;

        String sql = "UPDATE dbo.UserAccounts SET User_Status = ? WHERE UserID = ?";
        int rowAffected = jdbcTemplate.update(sql, status, userId);
        return rowAffected > 0;
    }

    public boolean updateUser(int userId, UpdateUserVM user) {
        if(!isExistUser(userId))
            return false;

        String sql = "UPDATE dbo.UserAccounts " +
                "SET User_Name = ?, User_Image = ?, User_Address = ?, User_Phone = ?, User_Sex = ?, " +
                "User_Date_Of_Birth = ?, User_More_Information = ?, User_Date_Update = ? " +
                "WHERE UserID = ?";
        int rowAffected = jdbcTemplate.update(sql, user.getUserName(), user.getUserImage(), user.getUserAddress(),
                user.getUserPhone(), user.isUserSex(), user.getUserDateOfBirth(), user.getUserMoreInformation(), Ultil.getCurrentDate(), userId);
        return rowAffected > 0;
    }
    public boolean isExistUser(int id){
        return getUserAccountById(id) != null;
    }
}
