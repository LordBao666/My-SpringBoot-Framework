package com.lordbao.bigevent.exception;


/**
 * @Author Lord_Bao
 * @Date 2025/2/1 17:14
 * @Version 1.0
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
