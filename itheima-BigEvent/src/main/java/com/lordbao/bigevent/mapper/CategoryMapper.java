package com.lordbao.bigevent.mapper;


import com.lordbao.bigevent.pojo.Category;
import com.lordbao.bigevent.pojo.dto.AddCategoryDTO;
import com.lordbao.bigevent.pojo.dto.UpdateCategoryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 17:18
 * @Version 1.0
 */
public interface CategoryMapper {

    public int addCategory( AddCategoryDTO categoryDTO);

    public  List<Category> getAllCategoriesByUserId(Integer userId);

    public Category getCategoryById(Integer id);

    public int updateCategory(UpdateCategoryDTO categoryDTO);

    public int deleteCategory(@Param("userId") Integer userId, @Param("id") Integer id);
}
