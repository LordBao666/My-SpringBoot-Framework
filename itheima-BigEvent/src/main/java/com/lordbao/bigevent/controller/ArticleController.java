package com.lordbao.bigevent.controller;


import com.lordbao.bigevent.pojo.Article;
import com.lordbao.bigevent.pojo.PageBean;
import com.lordbao.bigevent.service.ArticleService;
import com.lordbao.bigevent.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Lord_Bao
 * @Date 2025/2/12 16:57
 * @Version 1.0
 */
@RequestMapping("article")
@RestController
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated  Article article){

        int rows = articleService.addArticle(article);
        return rows>0? Result.success(): Result.error("因未知原因,添加失败");
    }

    @GetMapping
    public Result<PageBean<Article>> list(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          @RequestParam (required = false)String categoryId,
                                          @RequestParam (required = false)String state
                                          ){

        PageBean<Article> pageBean= articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);

    }


}
