package com.example.projectswp.repositories;

import com.example.projectswp.model.UserAddress;
import com.example.projectswp.repositories.rowMapper.UserAddressRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAddressRepository {
    private static final UserAddressRowMapper USER_ADDRESS_ROW_MAPPER = new UserAddressRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    public UserAddress getUserAddress(int uid) {
        String sql = "SELECT * FROM dbo.UserAddress WHERE UserID = ?";
        List<UserAddress> userAddress= jdbcTemplate.query(sql, USER_ADDRESS_ROW_MAPPER, uid);
        return userAddress.size() > 0 ? userAddress.get(0) : null;
    }

    public boolean createAddress(int uid, String address) {
        String sql = "INSERT INTO dbo.UserAddress(UserID, Address) VALUES(?, ?)";
        int rowAffected = jdbcTemplate.update(sql, uid, address);
        return rowAffected > 0;
    }
    public boolean deleteAddress(int uid, String location) {
        String sql = "DELETE dbo.UserAddress WHERE UserID = ? and Address = ?";
        int rowAffected = jdbcTemplate.update(sql, uid, location);
        return rowAffected > 0;
    }
}
