package com.lordbao.bigevent.pojo.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 17:24
 * @Version 1.0
 */
@Data
public class AddCategoryDTO {

    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名

    private Integer createUser;//创建的userId
}
