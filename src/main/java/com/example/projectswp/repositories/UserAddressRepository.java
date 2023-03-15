package com.example.projectswp.repositories;

import com.example.projectswp.model.UserAddress;
import com.example.projectswp.repositories.rowMapper.UserAddressRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAddressRepository {
    private static final UserAddressRowMapper USER_ADDRESS_ROW_MAPPER = new UserAddressRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    public UserAddress getUserAddress() {
        String sql = "";
        List<UserAddress> userAddress= jdbcTemplate.query(sql, USER_ADDRESS_ROW_MAPPER);
        return userAddress != null ? userAddress.get(0) : null;
    }

    public boolean createAddress() {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }
    public boolean deleteAddress() {
        String sql = "";
        int rowAffected = jdbcTemplate.update(sql);
        return rowAffected > 0;
    }
}
