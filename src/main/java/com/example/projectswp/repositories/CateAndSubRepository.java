package com.example.projectswp.repositories;

import com.example.projectswp.model.CateAndSub;
import com.example.projectswp.model.Category;
import com.example.projectswp.model.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CateAndSubRepository {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;

    public List<CateAndSub> getCateAndSubs() {

        List<CateAndSub> cateAndSubList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.getCategories();
        for (Category category : categoryList) {
            List<SubCategory> subCategories = subCategoryRepository.getSubCategoriesByCategory(category.getId());
            cateAndSubList.add(new CateAndSub(category.getId(), category.getName(), category.getImage(), subCategories));
        }
        return cateAndSubList.size() != 0 ? cateAndSubList : null;
    }

    public CateAndSub getCateAndSubByCategoryName(String name) {

        List<CateAndSub> cateAndSubList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.getCategoryByName(name);
        for (Category category : categoryList) {
            List<SubCategory> subCategories = subCategoryRepository.getSubCategoriesByCategory(category.getId());
            cateAndSubList.add(new CateAndSub(category.getId(), category.getName(), category.getImage(), subCategories));
        }
        return cateAndSubList.size() != 0 ? cateAndSubList.get(0) : null;
    }


}
