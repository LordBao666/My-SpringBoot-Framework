package com.lordbao.interceptors;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordbao.utils.JwtHelper;
import com.lordbao.utils.Result;
import com.lordbao.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author Lord_Bao
 * @Date 2024/9/18 17:41
 * @Version 1.0
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(jwtHelper.isExpiration(token)){
            Result r = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(r);
            response.getWriter().print(json);
            return false;
        }

        return true;
    }
}
