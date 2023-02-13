package com.example.projectswp.repositories.rowMapper;
import com.example.projectswp.model.Carts;
import com.example.projectswp.model.Images;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ImagesRowMapper implements RowMapper<Images>{
    @Override
    public Images mapRow(ResultSet rs, int rowNum) throws SQLException {
        Images images = new Images();
        images.setImageID(rs.getInt("ImageID"));
        images.setImageLink1(rs.getString("Image_Link_1"));
        images.setImageLink2(rs.getString("Image_Link_2"));
        images.setImageLink3(rs.getString("Image_Link_3"));
        images.setImageLink4(rs.getString("Image_Link_4"));
        images.setImageLink5(rs.getString("Image_Link_5"));
        return images;
    }
}
