package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.UserAddress;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressRowMapper implements RowMapper<UserAddress> {
    @Override
    public UserAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(rs.getInt("ID"));
        userAddress.setAddress(rs.getString("Address"));
        userAddress.setUserID(rs.getInt("UserID"));
        return userAddress;
    }
}
