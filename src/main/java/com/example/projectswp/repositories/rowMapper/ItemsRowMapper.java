package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Items;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsRowMapper implements RowMapper<Items> {

    @Override
    public Items mapRow(ResultSet rs, int rowNum) throws SQLException {
        Items items = new Items();

        items.setItemID(rs.getInt("ItemID"));
        items.setItemCode(rs.getString("Item_Code"));
        items.setUserId(rs.getInt("UserID"));
        items.setSubCategoryId(rs.getInt("Sub_CategoryID"));
        items.setItemTitle(rs.getString("Item_Title"));
        items.setItemDetailedDescription(rs.getString("Item_Detailed_Description"));
        items.setItemMass(rs.getDouble("Item_Mass"));
        items.setItemSize(rs.getBoolean("Item_Size"));
        items.setItemQuanlity(rs.getString("Item_Quanlity"));
        items.setItemEstimateValue(rs.getDouble("Item_Estimate_Value"));
        items.setItemSalePrice(rs.getDouble("Item_Sale_Price"));
        items.setItemShareAmount(rs.getInt("Item_Share_Amount"));
        items.setItemSponsoredOrderShippingFee(rs.getBoolean("Item_Sponsored_Order_Shipping_Fee"));
        items.setStringDateTimeExpired(rs.getDate("Item_Expired_Time"));
        items.setItemShippingAddress(rs.getString("Item_Shipping_Address"));
        items.setDateCreated(rs.getDate("Item_Date_Created"));
        items.setDateUpdate(rs.getDate("Item_Date_Update"));
        items.setStatus(rs.getBoolean("Item_Status"));
        items.setShare(rs.getBoolean("Share"));
        items.setImage(rs.getString("Image"));
        return items;
    }
}
