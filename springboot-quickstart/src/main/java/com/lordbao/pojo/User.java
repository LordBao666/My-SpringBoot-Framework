package com.lordbao.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Lord_Bao
 * @Date 2024/8/17 22:55
 * @Version 1.0
 */
@Data
@Component
public class User {
    @Value("${diy-user.username}")
    private String username;
    @Value("${diy-user.password}")
    private String password;
}
