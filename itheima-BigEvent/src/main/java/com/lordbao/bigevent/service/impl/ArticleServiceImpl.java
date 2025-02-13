package com.lordbao.bigevent.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lordbao.bigevent.mapper.ArticleMapper;
import com.lordbao.bigevent.pojo.Article;
import com.lordbao.bigevent.pojo.PageBean;
import com.lordbao.bigevent.service.ArticleService;
import com.lordbao.bigevent.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author Lord_Bao
 * @Date 2025/2/12 17:01
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int addArticle(Article article) {

        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer createUserid = (Integer) claims.get("id");
        article.setCreateUser(createUserid);
        return articleMapper.addArticle(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state) {

        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> claims= ThreadLocalUtil.get();
        Integer userId = (Integer)claims.get("id");

        List<Article> list =articleMapper.list(userId,categoryId,state);

        //pageInfo去封装list
        //注意上面的articleMapper并不会真的执行
        //实际上会被PageHelper拦截, 通过PageHelper完成
        PageInfo<Article> pageInfo = new PageInfo<>(list);


        PageBean<Article> pageBean = new PageBean<>();
        pageBean.setItems(pageInfo.getList());
        pageBean.setTotal(pageInfo.getTotal());

        return pageBean;
    }
}
