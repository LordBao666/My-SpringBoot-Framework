package com.lordbao.bigevent.exception;


import com.lordbao.bigevent.util.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Lord_Bao
 * @Date 2025/1/21 20:22
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        //StringUtils.hasLength(e.getMessage()) 等价于 判断
        //e.getMessage()!=null && e.getMessage().length>0
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage():"操作失败");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result handleUnauthorizedException(Exception e){
        e.printStackTrace();
        return Result.error("用户未登录");
    }

}
