package com.example.projectswp.repositories;

import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Carts;
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
        String sql = "select * from CartDetails where Cart_DetailID = ?";
        List<CartDetails> cartDetails = jdbcTemplate.query(sql,CART_DETAILS_ROW_MAPPER, cartDetailsID);
        return cartDetails.size() != 0? cartDetails.get(0): null;
    }
    public List<CartDetails> getCartDetails() {
        String sql = "Select * from CartDetails";
        List<CartDetails> cartDetails = jdbcTemplate.query(sql,CART_DETAILS_ROW_MAPPER);
        return cartDetails.size() != 0? cartDetails: null;
    }

    public boolean addCartDetails(CartDetails cartDetails) {
        String sql = "insert into dbo.CartDetails ([Cart_DetailID], [CartID], [ItemID], [Cart_Detail_Date_Create], [Cart_Detail_Date_Update], [Cart_Detail_Item_Quantity])\n" +
                "values (?, ?, ?, ?, ?, ?)";

        int check = jdbcTemplate.update(sql, cartDetails.getCartDetailsID(), cartDetails.getCartID(), cartDetails.getItemID(), getCurrentDate(), null, cartDetails.getCartDetailItemQuantity());
        return check != 0;
    }

    public boolean updateCartDetail(int cartDetailID, CartDetails cartDetails) {
        String sql = "update dbo.CartDetails\n" +
                "set CartID = ?,\n" +
                "    ItemID = ?,\n" +
                "    Cart_Detail_Date_Create = ?,\n" +
                "    Cart_Detail_Date_Update = ?,\n" +
                "    Cart_Detail_Item_Quantity = ?\n" +
                "where Cart_DetailID = ?";
        int check = jdbcTemplate.update(sql, cartDetails.getCartID(), cartDetails.getItemID(), cartDetails.getCartDetailDateCreate(), getCurrentDate(), cartDetails.getCartDetailItemQuantity(), cartDetailID);
        return check != 0;
    }
    public boolean deleteCartDetail(int cartDetailID){
        String sql = "DELETE dbo.CartDetails WHERE Cart_DetailID = ?";
        int check = jdbcTemplate.update(sql, cartDetailID);
        return check > 0;
    }
    public boolean acceptStatus(int cartDetailID) {
        String sql = "UPDATE dbo.CartDetails set Cart_Status = 2, Cart_Detail_Date_Update = ? WHERE Cart_DetailID = ?";
        int rowAffected = jdbcTemplate.update(sql, getCurrentDate(), cartDetailID);
        return rowAffected > 0;
    }
    public boolean cancelStatus(int cartDetailID) {
        String sql = "UPDATE dbo.CartDetails set Cart_Status = 3, Cart_Detail_Date_Update = ? WHERE Cart_DetailID = ?";
        int rowAffected = jdbcTemplate.update(sql, getCurrentDate(), cartDetailID);
        return rowAffected > 0;
    }
    public boolean confirmStatus(int cartDetailID) {
        String sql = "UPDATE dbo.CartDetails set Cart_Status = 3, Cart_Detail_Date_Update = ? WHERE Cart_DetailID = ?";
        int rowAffected = jdbcTemplate.update(sql, getCurrentDate(), cartDetailID);
        return rowAffected > 0;
    }
}