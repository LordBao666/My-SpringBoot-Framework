package com.lordbao.bigevent.pojo.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 19:12
 * @Version 1.0
 */
@Data
public class UpdateCategoryDTO {
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名

    @NotNull
    private Integer id;//文章id

    private Integer createUser;//创建的userId

}
