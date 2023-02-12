package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Items;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsRowMapper implements RowMapper<Items> {

    @Override
    public Items mapRow(ResultSet rs, int rowNum) throws SQLException {
        Items items = new Items();

        items.setID(rs.getInt("ItemID"));
        items.setItemCode(rs.getString("Item_Code"));
        items.setUserID(rs.getInt("UserID"));
        items.setSubCategoryID(rs.getInt("Sub_CategoryID"));
        items.setItemTitle(rs.getString("Item_Title"));
        items.setItemDetailedDescription(rs.getString("Item_Detailed_Description"));
        items.setMass(rs.getDouble("Item_Mass"));
        items.setSize(rs.getBoolean("Item_Size"));
        items.setStatus(rs.getString("Item_Status"));
        items.setValue(rs.getDouble("Item_Estimate_Value"));
        items.setPrice(rs.getDouble("Item_Sale_Price"));
        items.setAmount(rs.getInt("Item_Share_Amount"));
        items.setSponsoredOrderShippingFee(rs.getBoolean("Item_Sponsored_Order_Shipping_Fee"));
        items.setTime(rs.getDate("Item_Expired_Time"));
        items.setAddress(rs.getString("Item_Shipping_Address"));
        items.setDateCreated(rs.getDate("Item_Date_Created"));
        items.setDateUpdate(rs.getDate("Item_Date_Update"));
        items.setImageID(rs.getInt("ImageID"));
        return items;
    }
}
