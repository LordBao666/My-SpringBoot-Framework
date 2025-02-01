package com.lordbao.bigevent.mapper;


import com.lordbao.bigevent.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Lord_Bao
 * @Date 2025/1/21 10:06
 * @Version 1.0
 */
public interface UserMapper {
    public User findByUsername(String username);

    /**
     *
     * password是Service层加密后的密码,而非密码明文
     */
    public int add(@Param("username") String username, @Param("password") String password);

    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
