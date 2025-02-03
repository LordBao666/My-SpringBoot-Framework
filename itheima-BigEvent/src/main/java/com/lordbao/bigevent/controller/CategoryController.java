package com.lordbao.bigevent.controller;


import com.lordbao.bigevent.pojo.Category;
import com.lordbao.bigevent.pojo.dto.AddCategoryDTO;
import com.lordbao.bigevent.pojo.dto.UpdateCategoryDTO;
import com.lordbao.bigevent.service.CategoryService;
import com.lordbao.bigevent.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 17:14
 * @Version 1.0
 */
@RequestMapping("category")
@RestController
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated AddCategoryDTO categoryDTO){
        int rows=categoryService.addCategory(categoryDTO);
        return rows>0? Result.success():Result.error("因未知原因,添加分类失败");
    }

    @GetMapping
    public Result getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return Result.success(categories);
    }

    @GetMapping("detail")
    public Result getCategoryDetail(Integer id){
        Category category=categoryService.getCategoryById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result updateCategory(@Validated @RequestBody UpdateCategoryDTO categoryDTO){
        int rows=categoryService.updateCategory(categoryDTO);
        return rows>0? Result.success():Result.error("因未知原因,更新分类失败");
    }


    @DeleteMapping
    public Result deleteCategory(Integer id){
        int rows=categoryService.deleteCategoryById(id);
        return rows>0? Result.success():Result.error("因未知原因,删除分类失败");
    }
}
