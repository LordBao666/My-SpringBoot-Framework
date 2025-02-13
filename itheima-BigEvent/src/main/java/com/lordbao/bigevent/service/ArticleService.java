package com.lordbao.bigevent.service;


import com.lordbao.bigevent.pojo.Article;
import com.lordbao.bigevent.pojo.PageBean;

/**
 * @Author Lord_Bao
 * @Date 2025/2/12 17:00
 * @Version 1.0
 */
public interface ArticleService {

    public int   addArticle( Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);
}
