package com.lordbao.bigevent.service.impl;


import com.lordbao.bigevent.mapper.UserMapper;
import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.pojo.dto.RegisterUserDTO;
import com.lordbao.bigevent.pojo.dto.UpdateUserDTO;
import com.lordbao.bigevent.service.UserService;
import com.lordbao.bigevent.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author Lord_Bao
 * @Date 2025/1/21 10:06
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username,Md5Util.getMD5String(password));
    }

    @Override
    public int register(RegisterUserDTO userDTO) {
        //密码进行加密
        userDTO.setPassword(Md5Util.getMD5String(userDTO.getPassword()));
        return userMapper.add(userDTO);
    }

    @Override
    public int update(UpdateUserDTO userDTO) {
        return userMapper.update(userDTO);
    }
}
