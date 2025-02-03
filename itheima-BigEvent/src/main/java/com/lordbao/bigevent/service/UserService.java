package com.lordbao.bigevent.service;


import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.pojo.dto.RegisterUserDTO;
import com.lordbao.bigevent.pojo.dto.UpdateUserDTO;
import org.hibernate.validator.constraints.URL;

/**
 * @Author Lord_Bao
 * @Date 2025/1/21 10:06
 * @Version 1.0
 */
public interface UserService {
    public User findByUsername(String username);
    public User findByUsernameAndPassword(String username,String password);
    public int register(RegisterUserDTO userDTO);

    public int update(UpdateUserDTO userDTO);

    public int updateAvatar(@URL String avatarUrl);

    public int updatePwd(String newPwd);

}
