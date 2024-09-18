package com.lordbao.service;

import com.lordbao.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lordbao.pojo.vo.PortalVo;
import com.lordbao.utils.Result;

/**
* @author LordBao
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-09-06 14:38:11
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);
}
