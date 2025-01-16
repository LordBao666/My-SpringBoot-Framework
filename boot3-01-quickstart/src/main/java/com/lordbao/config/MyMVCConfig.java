package com.lordbao.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * @Author Lord_Bao
 * @Date 2024/10/2 14:51
 * @Version 1.0
 */
//@Configuration
//@EnableWebMvc //@EnableWebMVC 注解会禁用SpringBoot默认规则，因此手自一体时不要添加这个注解
public class MyMVCConfig  implements WebMvcConfigurer {

    //静态资源处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //如下代码可写可不写，默认都保留SpringBoot的静态资源配置
//        WebMvcConfigurer.super.addResourceHandlers(registry);


        registry.addResourceHandler("/static/**") //访问路径
                .addResourceLocations("classpath:/a/","classpath:/b/")//映射路径
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS));//缓存时间2小时
    }
}
