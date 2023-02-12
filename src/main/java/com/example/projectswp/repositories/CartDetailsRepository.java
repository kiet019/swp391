package com.example.projectswp.repositories;

import com.example.projectswp.model.CartDetails;
import com.example.projectswp.model.Carts;
import com.example.projectswp.repositories.rowMapper.CartDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CartDetailsRepository {
    private static final CartDetailsRowMapper  CART_DETAILS_ROW_MAPPER  = new CartDetailsRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

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
    public CartDetails getLastCartDetails() {
        List<CartDetails> list = getCartDetails();
        return list.size() != 0 ? list.get(list.size()-1) : null;
    }
    public boolean addCartDetails(CartDetails cartDetails) {
        String sql = "insert into dbo.CartDetails ([Cart_DetailID], [CartID], [ItemID], [Cart_Detail_Date_Create], [Cart_Detail_Date_Update], [Cart_Detail_Item_Quantity])\n" +
                "values (?, ?, ?, ?, ?, ?)";

        int check = jdbcTemplate.update(sql, cartDetails.getCartDetailsID(), cartDetails.getCartID(), cartDetails.getItemID(), cartDetails.getCartDetailDateCreate(), cartDetails.getCartDetailDateUpdate(), cartDetails.getCartDetailItemQuantity());
        return check != 0;
    }

    public boolean updateCart(CartDetails cartDetails) {
        String sql = "update dbo.CartDetails\n" +
                "set CartID = ?,\n" +
                "    ItemID = ?\n" +
                "    Cart_Detail_Date_Create = ?\n" +
                "    Cart_Detail_Date_Update = ?\n" +
                "    Cart_Detail_Item_Quantity = ?\n" +
                "where Cart_DetailID = ?";
        int check = jdbcTemplate.update(sql, cartDetails.getCartID(), cartDetails.getItemID(), cartDetails.getCartDetailDateCreate(), cartDetails.getCartDetailDateUpdate(), cartDetails.getCartDetailItemQuantity(), cartDetails.getCartDetailsID());
        return check != 0;
    }

}