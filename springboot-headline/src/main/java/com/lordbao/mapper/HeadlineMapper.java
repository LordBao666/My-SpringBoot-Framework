package com.lordbao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lordbao.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lordbao.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author LordBao
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-09-06 14:38:11
* @Entity com.lordbao.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    Page<Map> selectHeadlinePage(IPage<PortalVo> page, @Param("portalVo") PortalVo portalVo);

    Map<String, Object> selectHeadlineDetail(@Param("hid") Integer hid);
}




