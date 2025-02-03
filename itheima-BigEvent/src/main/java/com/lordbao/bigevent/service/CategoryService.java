package com.lordbao.bigevent.service;


import com.lordbao.bigevent.pojo.Category;
import com.lordbao.bigevent.pojo.dto.AddCategoryDTO;
import com.lordbao.bigevent.pojo.dto.UpdateCategoryDTO;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 17:17
 * @Version 1.0
 */
public interface CategoryService {



    public int addCategory(AddCategoryDTO categoryDTO);

    public List<Category> getAllCategories();

    public Category getCategoryById(Integer id);

    public int updateCategory(UpdateCategoryDTO categoryDTO);

    public int deleteCategoryById(Integer id);
}
