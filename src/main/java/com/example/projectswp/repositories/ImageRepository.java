package com.example.projectswp.repositories;

import com.example.projectswp.model.Images;
import com.example.projectswp.repositories.rowMapper.ImagesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository {
    private static final ImagesRowMapper IMAGE_ROW_MAPPER = new ImagesRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Images getLastImage() {
        List<Images> imagesList = getImages();
        return imagesList.size() != 0 ? imagesList.get(imagesList.size()-1) : null;
    }
    public Images getImage(int imageID) {
        String sql = "select * from Images where ImageID = ?";
        List<Images> images = jdbcTemplate.query(sql,IMAGE_ROW_MAPPER, imageID);
        return images.size() != 0? images.get(0): null;
    }
    public List<Images> getImages() {
        String sql = "Select * from Images";
        List<Images> images = jdbcTemplate.query(sql,IMAGE_ROW_MAPPER);
        return images.size() != 0? images: null;
    }
    public boolean addImage(Images images) {
        String sql = "insert into dbo.Images ([Image_Link_1], [Image_Link_2], [Image_Link_3], [Image_Link_4], [Image_Link_5])\n" +
                "values (?, ?, ?, ?, ?)";

        int check = jdbcTemplate.update(sql, images.getImageLink1(), images.getImageLink2(), images.getImageLink3(), images.getImageLink3(), images.getImageLink4(), images.getImageLink5());
        return check != 0;
    }
    public boolean updateImage(int imageID, Images images) {
        String sql = "update dbo.Images\n" +
                "set Image_Link_1 = ?,\n" +
                "    Image_Link_2 = ?\n" +
                "    Image_Link_3 = ?\n" +
                "    Image_Link_4 = ?\n" +
                "    Image_Link_5 = ?\n" +
                "where ImageID = ?";
        int check = jdbcTemplate.update(sql, images.getImageLink1(), images.getImageLink2(), images.getImageLink3(), images.getImageLink4(), images.getImageLink5(), images.getImageID());
        return check != 0;
    }
    public boolean deleteImage(int imageID){
        String sql = "DELETE dbo.Images WHERE ImageID = ?";
        int check = jdbcTemplate.update(sql, imageID);
        return check > 0;
    }
}
