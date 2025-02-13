package com.lordbao.bigevent.mapper;


import com.lordbao.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/2/12 17:02
 * @Version 1.0
 */
public interface ArticleMapper {

    public int addArticle(Article article);

    List<Article> list(@Param("userId") Integer userId, @Param("categoryId") String categoryId, @Param("state") String state);
}
