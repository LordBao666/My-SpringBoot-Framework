package com.lordbao.controller;


import com.lordbao.pojo.Headline;
import com.lordbao.service.HeadlineService;
import com.lordbao.utils.JwtHelper;
import com.lordbao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Lord_Bao
 * @Date 2024/9/18 17:05
 * @Version 1.0
 */
@RequestMapping("headline")
@RestController
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){

        int uid = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(uid);

        return headlineService.publish(headline);
    }
}
