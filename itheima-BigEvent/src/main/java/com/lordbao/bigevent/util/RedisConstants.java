package com.lordbao.bigevent.util;


/**
 * @Author Lord_Bao
 * @Date 2025/2/16 12:27
 * @Version 1.0
 *
 * 未明确指明的话,过期时间都是min
 */
public class RedisConstants {

    //user 将输入用户的用户id
    //device 将输入用户的设备id
    public static final String  USER_LOGIN_KEY = "itheima-big-event:login:user:%s:device:%s";

    //用户登录的有效时长为12小时。下面的单位是min.
    public static final Long USER_LOGIN_EXPIRE = 60L * 12;
}
