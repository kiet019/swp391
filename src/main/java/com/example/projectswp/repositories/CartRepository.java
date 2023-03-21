package com.example.projectswp.repositories;

import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.rowMapper.CartsRowMapper;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class CartRepository {
    private static final CartsRowMapper CART_ROW_MAPPER = new CartsRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserAccountRepository userAccountRepository;
    public List<Carts> getCarts() {
        String sql = "Select * from Carts where UserID = ?";
        List<Carts> cart = jdbcTemplate.query(sql,CART_ROW_MAPPER, Ultil.getUserId());
        return cart.size() != 0? cart: null;
    }
    public Carts getLastCart() {
        List<Carts> list = getCarts();
        return list.size() != 0 ? list.get(list.size()-1) : null;
    }
    public boolean addCart(int userID) {
        String sql = "insert into dbo.Carts ([UserID], [Address])\n" +
                "values (?, ?)";
        int check = jdbcTemplate.update(sql, userID, userAccountRepository.getUserAddress(userID));
        return check != 0;
    }
    public boolean updateCartAddress(String address, int userID) {
        String sql = " update Carts\n" +
                "  set Address = ?\n" +
                "  where UserID = ?";
        int check = jdbcTemplate.update(sql, address, userID);
        return check >0 ? true : false;
    }

    public String getAddress(int userID) {
        String sql = "Select * from Carts where UserID = ?";
        List<Carts> cart = jdbcTemplate.query(sql,CART_ROW_MAPPER, Ultil.getUserId());
        return cart.size() != 0? cart.get(0).getAddress() : null;
    }
}
