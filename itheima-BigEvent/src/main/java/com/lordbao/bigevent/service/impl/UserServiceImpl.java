package com.lordbao.bigevent.service.impl;


import com.lordbao.bigevent.mapper.UserMapper;
import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.pojo.dto.RegisterUserDTO;
import com.lordbao.bigevent.pojo.dto.UpdateUserDTO;
import com.lordbao.bigevent.service.UserService;
import com.lordbao.bigevent.util.Md5Util;
import com.lordbao.bigevent.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public int updateAvatar(String avatarUrl) {
        Map<String,Object> claims  = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return userMapper.updateAvatar(id,avatarUrl);
    }

    @Override
    public int updatePwd(String newPwd) {
        Map<String,Object> claims= ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        //对新密码加密
        return userMapper.updatePWd(Md5Util.getMD5String(newPwd),id);
    }
}
