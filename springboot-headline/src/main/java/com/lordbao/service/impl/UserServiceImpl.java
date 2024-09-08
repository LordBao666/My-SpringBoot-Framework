package com.lordbao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.pojo.User;
import com.lordbao.service.UserService;
import com.lordbao.mapper.UserMapper;
import com.lordbao.utils.JwtHelper;
import com.lordbao.utils.MD5Util;
import com.lordbao.utils.Result;
import com.lordbao.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author LordBao
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-09-06 14:38:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserMapper userMapper;

    public Result login(User user){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(wrapper);

        //账户不存在
        if(loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //密码核对正确
        if(loginUser.getUserPwd()!= null &&
                loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))){
            Map map  = new HashMap();
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            map.put("token",token);
            return Result.ok(map);
        }

        //密码核对失败
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        //如果token过期
        if(jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        Long userId = jwtHelper.getUserId(token);
        User user = userMapper.selectById(userId);
        //token合法
        if(user!=null){
            Map map = new HashMap();
            map.put("loginUser",user);
            return Result.ok(map);
        }

        return Result.build(null,ResultCodeEnum.NOTLOGIN);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(wrapper);
        //用户名已经被占用
        if(user!=null){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result regist(User user) {
        Result checkUserNameResult = checkUserName(user.getUsername());
        //用户名被占用
        if(!Objects.equals(checkUserNameResult.getCode(), ResultCodeEnum.SUCCESS.getCode())){
            return checkUserNameResult;
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}




