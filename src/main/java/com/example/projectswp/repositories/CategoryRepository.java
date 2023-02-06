package com.example.projectswp.repositories;

import com.example.projectswp.model.Category;
import com.example.projectswp.rowmapper.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private static final CategoryRowMapper CATEGORY_ROW_MAPPER = new CategoryRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Category getCategory(int id) {
        String sql = "select * from Categories where CategoryID = ?";

        List<Category> list = jdbcTemplate.query(sql, CATEGORY_ROW_MAPPER, id);

        return list.size() != 0 ? list.get(0) : null;
    }
    public Category getLastCategory() {
        List<Category> list = this.getCategories();
        return list.size() != 0 ? list.get(list.size()-1) : null;
    }
    public List<Category> getCategories() {
        String sql = "select * from Categories";

        List<Category> list = jdbcTemplate.query(sql, CATEGORY_ROW_MAPPER);

        return list.size() != 0 ? list : null;
    }

    public boolean addCategory(Category category) {
        String sql = "insert into dbo.Categories ([Category_Name], [Category_Image])\n" +
                "values (?, ?)";

        int check = jdbcTemplate.update(sql, category.getName(), category.getImage());
        return check !=0 ? true : false;
    }

    public boolean updateCategory(Category category) {
        String sql = "update dbo.Categories\n" +
                "set Category_Name = ?,\n" +
                "    Category_Image = ?\n" +
                "where CategoryID = ?";
        int check = jdbcTemplate.update(sql, category.getName(), category.getImage(), category.getId());
        return check !=0 ? true : false;
    }
}
