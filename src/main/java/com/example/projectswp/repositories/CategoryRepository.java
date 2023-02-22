package com.example.projectswp.repositories;

import com.example.projectswp.model.Category;
import com.example.projectswp.repositories.rowMapper.CategoryRowMapper;
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
    public List<Category> getCategoryByStatus(Boolean status) {
        String sql = "select * from Categories where Category_Status = ?";

        List<Category> list = jdbcTemplate.query(sql, CATEGORY_ROW_MAPPER, status);

        return list.size() != 0 ? list : null;
    }
    public List<Category> getCategoryByName(String name) {
        String sql = "select * from Categories where Category_Name = ?";

        List<Category> list = jdbcTemplate.query(sql, CATEGORY_ROW_MAPPER, name);

        return list.size() != 0 ? list : null;
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
        String sql = "insert into dbo.Categories ([Category_Name], [Category_Image], [Category_Status])\n" +
                "values (?, ?, ?)";

        int check = jdbcTemplate.update(sql, category.getName(), category.getImage(), true);
        return check != 0;
    }

    public boolean updateCategory(int id ,Category category) {
        String sql = "update dbo.Categories\n" +
                "set Category_Name = ?,\n" +
                "    Category_Image = ?\n" +
                "where CategoryID = ?";
        int check = jdbcTemplate.update(sql, category.getName(), category.getImage(), id);
        return check != 0;
    }
    public boolean deleteCategory(int id) {
        String sql = "update dbo.Categories\n" +
                "set Category_Status = ?\n" +
                "where CategoryID = ?";
        int check = jdbcTemplate.update(sql, false, id);
        return check != 0;
    }

}
