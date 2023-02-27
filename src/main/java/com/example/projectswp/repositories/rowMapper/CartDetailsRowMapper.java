package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Carts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDetailsRowMapper implements RowMapper<CartDetails> {
    @Override
    public CartDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setCartDetailsID(rs.getInt("Cart_DetailID"));
        cartDetails.setCartID(rs.getInt("CartID"));
        cartDetails.setItemID(rs.getInt("ItemID"));
        cartDetails.setCartDetailDateCreate(rs.getDate("Cart_Detail_Date_Create"));
        cartDetails.setCartDetailDateUpdate(rs.getDate("Cart_Detail_Date_Update"));
        cartDetails.setCartDetailItemQuantity(rs.getInt("Cart_Detail_Item_Quantity"));
        cartDetails.setCartStatus(rs.getInt("Cart_Status"));
        return cartDetails;
    }
}
