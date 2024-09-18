package com.lordbao.controller;

import com.lordbao.pojo.Type;
import com.lordbao.pojo.vo.PortalVo;
import com.lordbao.service.HeadlineService;
import com.lordbao.service.TypeService;
import com.lordbao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/9/8 16:36
 * @Version 1.0
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    /**
     * 查询全部类别信息
     *
     */
    @GetMapping("findAllTypes")
    public Result findAllTypes(){

        List<Type> list = typeService.list();
        return Result.ok(list);
    }

    /**
     * 首页分页查询
     *
     */
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        return headlineService.findNewsPage(portalVo);
    }


    /**
     * 首页详情接口
     * @param hid
     */
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
