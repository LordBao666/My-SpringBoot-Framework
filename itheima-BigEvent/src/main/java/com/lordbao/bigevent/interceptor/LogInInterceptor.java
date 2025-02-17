package com.lordbao.bigevent.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordbao.bigevent.exception.UnauthorizedException;
import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.util.JSONHelper;
import com.lordbao.bigevent.util.JwtUtil;
import com.lordbao.bigevent.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lord_Bao
 * @Date 2025/2/1 16:12
 * @Version 1.0
 */

public class LogInInterceptor implements HandlerInterceptor {


    private StringRedisTemplate stringRedisTemplate;

    public LogInInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        try{

            if(token==null || token.isEmpty()){
                 throw new UnauthorizedException("用户未登录");
            }
            String value = stringRedisTemplate.opsForValue().get(token);
            if(value==null){
                throw  new UnauthorizedException("token已经失效,请用户重新登录");
            }

            User user = (User) JSONHelper.readValue(value,User.class);

            Map<String, Object> claims = new HashMap<>();
            claims.put("username",user.getUsername());
            claims.put("id",user.getId());

            ThreadLocalUtil.set(claims);//将claims存储在ThreadLocal中
            return true;//放行
        }catch (Exception e){//报错,说明必然验证失败
            response.setStatus(401);
            throw new UnauthorizedException("用户未登录");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       ThreadLocalUtil.remove();//回收资源
    }
}
