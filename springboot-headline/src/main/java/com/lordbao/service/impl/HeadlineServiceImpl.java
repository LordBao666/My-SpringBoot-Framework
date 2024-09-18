package com.lordbao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.pojo.Headline;
import com.lordbao.pojo.vo.PortalVo;
import com.lordbao.service.HeadlineService;
import com.lordbao.mapper.HeadlineMapper;
import com.lordbao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author LordBao
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-09-06 14:38:11
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private  HeadlineMapper headlineMapper;
    @Override
    public Result findNewsPage(PortalVo portalVo) {


        Page<PortalVo> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());

        Page<Map> resultPage = headlineMapper.selectHeadlinePage(page, portalVo);

        Map<String,Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData",resultPage.getRecords());
        pageInfo.put("pageNum",resultPage.getCurrent());
        pageInfo.put("pageSize",resultPage.getSize());
        pageInfo.put("totalPage",resultPage.getPages());
        pageInfo.put("totalSize",resultPage.getTotal());


        Map<String,Object> data = new HashMap<>();
        data.put("pageInfo",pageInfo);
        return Result.ok(data);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map<String,Object> map =headlineMapper.selectHeadlineDetail(hid);
        Integer pageViews = (Integer) map.get("pageViews");
        map.put("pageViews",pageViews+1);//pageViews +1

        //headline views + 1
        Headline headline = new Headline();
        headline.setHid((Integer) map.get("hid"));
        headline.setPageViews(pageViews+1);
        //这里是为了乐观锁更新，所以需要version
        headline.setVersion((Integer) map.get("version"));
        headlineMapper.updateById(headline);


        //去除掉version,因为它并非要求返回的数据
        map.remove("version");
        Map<String,Object> data = new HashMap<>();
        data.put("headline",map);
        return Result.ok(data);
    }
}




