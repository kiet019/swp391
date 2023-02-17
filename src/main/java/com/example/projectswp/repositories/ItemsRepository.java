package com.example.projectswp.repositories;

import com.example.projectswp.model.Items;
import com.example.projectswp.repositories.rowMapper.ItemsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class ItemsRepository {
    private static final ItemsRowMapper ITEMS_ROW_MAPPER = new ItemsRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Date getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return new Date(dtf.format(now));
    }
    public Items getItem(int itemID) {
        String sql = "select * from Items where ItemID = ?";
        List<Items> items = jdbcTemplate.query(sql,ITEMS_ROW_MAPPER, itemID);
        return items.size() != 0? items.get(0): null;
    }
    public List<Items> getItems() {
        String sql = "Select * from Items";
        List<Items> items = jdbcTemplate.query(sql,ITEMS_ROW_MAPPER);
        return items.size() != 0? items: null;
    }
    public Items getLastItem() {
        List<Items> list = getItems();
        return list.size() != 0 ? list.get(list.size()-1) : null;
    }
    public boolean addItems(Items item) {
        String sql = "insert into dbo.Items ([Item_Code], [UserID], [Sub_CategoryID], [Item_Title], [Item_Detailed_Description], [Item_Mass], [Item_Size], [Item_Status], [Item_Estimate_Value], [Item_Sale_Price], [Item_Share_Amount], [Item_Sponsored_Order_Shipping_Fee], [Item_Expired_Time], [Item_Shipping_Address], [Item_Date_Created], [Item_Date_Update], [ImageID])\n" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int check = jdbcTemplate.update(sql, item.getItemCode(), item.getUserID(), item.getSubCategoryID(), item.getItemTitle(), item.getItemDetailedDescription(), item.getMass(), item.isSize(),  1, item.getValue(), item.getPrice(), item.getAmount(), item.isSponsoredOrderShippingFee(), item.getTime(), item.getAddress(), getCurrentDate(), null, item.getImageID());
        return check != 0;
    }

    public boolean updateItems(int itemID,Items item) {
        String sql = "update dbo.Items\n" +
                "set Item_Code = ?,\n" +
                "    UserID = ?,\n" +
                "    Sub_CategoryID = ?,\n" +
                "    Item_Title = ?,\n" +
                "    Item_Detailed_Description = ?,\n" +
                "    Item_Mass = ?,\n" +
                "    Item_Size = ?,\n" +
                "    Item_Status = ?,\n" +
                "    Item_Estimate_Value = ?,\n" +
                "    Item_Sale_Price = ?,\n" +
                "    Item_Share_Amount = ?,\n" +
                "    Item_Sponsored_Order_Shipping_Fee = ?,\n" +
                "    Item_Expired_Time = ?,\n" +
                "    Item_Shipping_Address = ?,\n" +
                "    Item_Date_Created = ?,\n" +
                "    Item_Date_Update = ?,\n" +
                "    ImageID = ?\n" +
                "where ItemID = ?";
        int check = jdbcTemplate.update(sql, item.getItemCode(), item.getUserID(), item.getSubCategoryID(), item.getItemTitle(), item.getItemDetailedDescription(), item.getMass(), item.isSize(),  item.getStatus(), item.getValue(), item.getPrice(), item.getAmount(), item.isSponsoredOrderShippingFee(), item.getTime(), item.getAddress(), item.getDateCreated(), getCurrentDate(), item.getImageID(), itemID);
        return check != 0;
    }
    public boolean deleteImage(int itemID){
        String sql = "DELETE dbo.Items WHERE ItemID = ?";
        int check = jdbcTemplate.update(sql, itemID);
        return check > 0;
    }

}
