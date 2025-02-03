package com.lordbao.bigevent.service.impl;


import com.lordbao.bigevent.mapper.CategoryMapper;
import com.lordbao.bigevent.pojo.Category;
import com.lordbao.bigevent.pojo.dto.AddCategoryDTO;
import com.lordbao.bigevent.pojo.dto.UpdateCategoryDTO;
import com.lordbao.bigevent.service.CategoryService;
import com.lordbao.bigevent.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 17:17
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(AddCategoryDTO categoryDTO){
       Map<String,Object> claims =  ThreadLocalUtil.get();
       Integer createUser = (Integer) claims.get("id");
       //增加 添加给分类的用户id
       categoryDTO.setCreateUser(createUser);

       return categoryMapper.addCategory(categoryDTO);
    }

    @Override
    public List<Category> getAllCategories() {
        Map<String,Object> claims =  ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        return categoryMapper.getAllCategoriesByUserId(userId);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public int updateCategory(UpdateCategoryDTO categoryDTO) {
        Map<String,Object> claims =  ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        categoryDTO.setCreateUser(userId);
        return categoryMapper.updateCategory(categoryDTO);
    }

    @Override
    public int deleteCategoryById(Integer id) {
        Map<String,Object> claims =  ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        return categoryMapper.deleteCategory(userId,id);
    }


}
