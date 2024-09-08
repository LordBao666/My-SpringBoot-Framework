package com.lordbao.service;

import com.lordbao.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lordbao.utils.Result;

/**
 * @author LordBao
 * @description 针对表【news_user】的数据库操作Service
 * @createDate 2024-09-06 14:38:11
 */
public interface UserService extends IService<User> {
    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
