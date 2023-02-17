package com.example.projectswp.repositories;

import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.rowMapper.CartsRowMapper;
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
    public Date getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return new Date(dtf.format(now));
    }
    public Carts getCart(int cartID) {
        String sql = "select * from Carts where CartID = ?";
        List<Carts> cart = jdbcTemplate.query(sql,CART_ROW_MAPPER, cartID);
        return cart.size() != 0? cart.get(0): null;
    }
    public List<Carts> getCarts() {
        String sql = "Select * from Carts";
        List<Carts> cart = jdbcTemplate.query(sql,CART_ROW_MAPPER);
        return cart.size() != 0? cart: null;
    }
    public Carts getLastCart() {
        List<Carts> list = getCarts();
        return list.size() != 0 ? list.get(list.size()-1) : null;
    }
    public boolean addCart(Carts cart) {
        String sql = "insert into dbo.Carts ([CartID], [Cart_Date_Create])\n" +
                "values (?, ?)";
        int check = jdbcTemplate.update(sql, cart.getCartID(), getCurrentDate());
        return check != 0;
    }


    public boolean updateCart(int cartID , Carts cart) {
        String sql = "update dbo.Carts\n" +
                "set Cart_Date_Create = ?\n" +
                "where CartID = ?";
        int check = jdbcTemplate.update(sql, cart.getCartDateCreate(), cartID);
        return check > 0;
    }
    public boolean deleteCart(int cartID){
        String sql = "DELETE dbo.Carts WHERE CartID = ?";
        int check = jdbcTemplate.update(sql, cartID);
        return check > 0;
    }

}
