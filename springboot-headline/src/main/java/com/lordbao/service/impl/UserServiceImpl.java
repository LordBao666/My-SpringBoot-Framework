package com.lordbao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.pojo.User;
import com.lordbao.service.UserService;
import com.lordbao.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author LordBao
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-09-06 14:38:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




