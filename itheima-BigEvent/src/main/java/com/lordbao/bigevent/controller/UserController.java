package com.lordbao.bigevent.controller;

import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.pojo.dto.RegisterUserDTO;
import com.lordbao.bigevent.pojo.dto.UpdateUserDTO;
import com.lordbao.bigevent.service.UserService;
import com.lordbao.bigevent.util.JwtUtil;
import com.lordbao.bigevent.util.Result;
import com.lordbao.bigevent.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public Result register(@Validated RegisterUserDTO userDTO) {

        String username = userDTO.getUsername();
        //根据上下文来看,username是唯一的
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.error(username + "已经存在!");
        } else {
            int rows = userService.register(userDTO);
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


    @GetMapping("userinfo")
    public Result userinfo(){
        Map<String,Object> claims  = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User retUser = userService.findByUsername(username);

        User safeUser = new User();
        safeUser.setId(retUser.getId());
        safeUser.setUsername(retUser.getUsername());
        safeUser.setNickname(retUser.getNickname());
        safeUser.setEmail(retUser.getEmail());
        safeUser.setUserPic(retUser.getUserPic());
        safeUser.setCreateTime(retUser.getCreateTime());
        safeUser.setUpdateTime(retUser.getUpdateTime());


        return Result.success(safeUser);
    }



    @PutMapping("update")
    public Result update(@RequestBody @Validated UpdateUserDTO userDTO){
        int rows=userService.update(userDTO);
        return rows>0?Result.success():Result.error("因未知原因,更新失败");
    }
}
