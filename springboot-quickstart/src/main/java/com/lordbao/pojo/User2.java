package com.lordbao.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/17 23:00
 * @Version 1.0
 */
@Component
@Data
@ConfigurationProperties(prefix = "diy-user")
public class User2 {
    private String username;
    private String password;
    private List<String> hobbies;
}
