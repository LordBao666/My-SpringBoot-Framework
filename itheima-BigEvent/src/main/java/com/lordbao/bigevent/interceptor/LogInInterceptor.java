package com.lordbao.bigevent.interceptor;


import com.lordbao.bigevent.exception.UnauthorizedException;
import com.lordbao.bigevent.util.JwtUtil;
import com.lordbao.bigevent.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @Author Lord_Bao
 * @Date 2025/2/1 16:12
 * @Version 1.0
 */
@Component
public class LogInInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try{
            Map<String, Object> claims = JwtUtil.parseToken(token);
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
