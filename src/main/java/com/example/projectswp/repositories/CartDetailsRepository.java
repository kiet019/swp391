package com.example.projectswp.repositories;

import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.rowMapper.CartDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Repository
public class CartDetailsRepository {
    private static final CartDetailsRowMapper  CART_DETAILS_ROW_MAPPER  = new CartDetailsRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Date getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return new Date(dtf.format(now));
    }
    public CartDetails getLastCartDetails() {
        List<CartDetails> list = getCartDetails();
        return list.size() != 0 ? list.get(list.size()-1) : null;
    }
    public CartDetails getCartDetail(int cartDetailsID) {
        String sql = "select * from CartDetails where CartDetailID = ?";
        List<CartDetails> cartDetails = jdbcTemplate.query(sql,CART_DETAILS_ROW_MAPPER, cartDetailsID);
        return cartDetails.size() != 0? cartDetails.get(0): null;
    }
    public List<CartDetails> getCartDetails() {
        String sql = "Select * from CartDetails";
        List<CartDetails> cartDetails = jdbcTemplate.query(sql,CART_DETAILS_ROW_MAPPER);
        return cartDetails.size() != 0? cartDetails: null;
    }

    public boolean addCartDetails(CartDetails cartDetails) {
        String sql = "insert into dbo.CartDetails ([CartID], [ItemID], [ItemQuantity])\n" +
                "values (?, ?, ?)";

        int check = jdbcTemplate.update(sql, cartDetails.getCartId(), cartDetails.getItemId(), 1);
        return check != 0;
    }
    public boolean updateCartDetail(CartDetails cartDetails) {
        String sql = "update dbo.CartDetails\n" +
                "set ItemQuantity = ?\n" +
                "where CartDetailID = ?";
        int check = jdbcTemplate.update(sql,cartDetails.getItemQuantity(), cartDetails.getCartDetailId());
        return check != 0;
    }

    public boolean deleteCartDetail(int id){
        String sql = "DELETE dbo.CartDetails WHERE CartDetailID = ?";
        int check = jdbcTemplate.update(sql, id);
        return check > 0;
    }
}