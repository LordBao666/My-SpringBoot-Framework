package com.lordbao.bigevent.controller;


import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.service.UserService;
import com.lordbao.bigevent.util.JwtUtil;
import com.lordbao.bigevent.util.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author Lord_Bao
 * @Date 2025/1/21 10:05
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("register")
    public Result register(@Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名只允许字母、数字和下划线，且长度在5到16之间") String username,
                           @Pattern(regexp = "^[a-zA-Z0-9_.]{5,16}$", message = "密码可以包含字母、数字、下划线和点，且长度在5到16之间") String password) {

        //根据上下文来看,username是唯一的
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.error(username + "已经存在!");
        } else {
            int rows = userService.register(username, password);
            return rows > 0 ? Result.success() : Result.error("因未知原因,添加失败");
        }
    }

    @PostMapping("login")
    public Result login(@Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名只允许字母、数字和下划线，且长度在5到16之间") String username,
                        @Pattern(regexp = "^[a-zA-Z0-9_.]{5,16}$", message = "密码可以包含字母、数字、下划线和点，且长度在5到16之间") String password) {

        //根据上下文来看,username是唯一的

        User user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);//返回token信息
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
