package com.lordbao.bigevent.config;


import com.lordbao.bigevent.interceptor.LogInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Lord_Bao
 * @Date 2025/2/1 16:20
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //logInInterceptor 仅不拦截/user/login, /user/register
        registry.addInterceptor(new LogInInterceptor(stringRedisTemplate)).excludePathPatterns("/user/login","/user/register");
    }
}
