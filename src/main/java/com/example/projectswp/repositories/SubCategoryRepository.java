package com.example.projectswp.repositories;

import com.example.projectswp.model.Category;
import com.example.projectswp.model.SubCategory;
import com.example.projectswp.repositories.rowMapper.SubCategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubCategoryRepository {
    private static final SubCategoryRowMapper SUB_CATEGORY_ROW_MAPPER = new SubCategoryRowMapper();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public SubCategory getSubCategory(int id) {
        String sql = "select * from SubCategories where Sub_CategoryID = ?";

        List<SubCategory> list = jdbcTemplate.query(sql, SUB_CATEGORY_ROW_MAPPER, id);

        return list.size() != 0 ? list.get(0) : null;
    }
    public List<SubCategory> getSubCategoryByName(String name) {
        String sql = "select * from SubCategories where Sub_Category_Name like ?";

        List<SubCategory> list = jdbcTemplate.query(sql, SUB_CATEGORY_ROW_MAPPER, "%" +name + "%");

        return list.size() != 0 ? list : null;
    }

    public List<SubCategory> getSubCategories() {
        String sql = "select * from SubCategories";

        List<SubCategory> list = jdbcTemplate.query(sql, SUB_CATEGORY_ROW_MAPPER);

        return list.size() != 0 ? list : null;
    }

    public boolean addSubCategory(SubCategory subCategory) {
        String sql = "insert into SubCategories([categoryID],[Sub_Category_Name], [Sub_Category_Status])\n" +
                "values (?, ?, ?)";

        int check = jdbcTemplate.update(sql, subCategory.getCategoryID(), subCategory.getName(), true);
        return check !=0 ? true : false;
    }

    public boolean updateSubCategory(SubCategory subCategory) {
        String sql = "update dbo.SubCategories\n" +
                "set CategoryID = ?,\n" +
                "    Sub_Category_Name = ?\n" +
                "where Sub_CategoryID = ?";
        int check = jdbcTemplate.update(sql, subCategory.getCategoryID(), subCategory.getName(), subCategory.getId());
        return check !=0 ? true : false;
    }

    public boolean deleteSubCategory(SubCategory subCategory) {
        String sql = "update dbo.SubCategories\n" +
                "set Sub_Category_Status = ?\n" +
                "where Sub_CategoryID = ?";
        int check = jdbcTemplate.update(sql, false, subCategory.getId());
        return check !=0 ? true : false;
    }

    public List<SubCategory> getSubCategoriesByCategory(int categoryId) {
        String sql = "select * from SubCategories where CategoryID = ?";

        List<SubCategory> list = jdbcTemplate.query(sql, SUB_CATEGORY_ROW_MAPPER, categoryId);

        return list.size() != 0 ? list : null;
    }
}
