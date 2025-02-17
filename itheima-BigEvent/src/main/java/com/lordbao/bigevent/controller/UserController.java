package com.lordbao.bigevent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordbao.bigevent.pojo.User;
import com.lordbao.bigevent.pojo.dto.RegisterUserDTO;
import com.lordbao.bigevent.pojo.dto.UpdateUserDTO;
import com.lordbao.bigevent.service.UserService;
import com.lordbao.bigevent.util.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    //未引入Redis 来充当token的登录密码
//    @PostMapping("login")
//    public Result login(@Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名只允许字母、数字和下划线，且长度在5到16之间") String username,
//                        @Pattern(regexp = "^[a-zA-Z0-9_.]{5,16}$", message = "密码可以包含字母、数字、下划线和点，且长度在5到16之间") String password) {
//
//        //根据上下文来看,username是唯一的
//
//        User user = userService.findByUsernameAndPassword(username, password);
//        if (user != null) {
//            HashMap<String, Object> claims = new HashMap<>();
//            claims.put("id",user.getId());
//            claims.put("username",user.getUsername());
//            String token = JwtUtil.genToken(claims);
//            return Result.success(token);//返回token信息
//        } else {
//            return Result.error("用户名或密码错误");
//        }
//    }

//    引入Redis 来充当token的登录密码
    @PostMapping("login")
    public Result login(@Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名只允许字母、数字和下划线，且长度在5到16之间") String username,
                        @Pattern(regexp = "^[a-zA-Z0-9_.]{5,16}$", message = "密码可以包含字母、数字、下划线和点，且长度在5到16之间") String password,
                        @RequestHeader("Device-ID") String deviceID) throws JsonProcessingException {

        //获取设备信息
        if(deviceID==null || deviceID.isEmpty()){
            throw new RuntimeException("设备id不能为空");
        }


        //根据上下文来看,username是唯一的
        User user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {


            //生成Token,格式为 itheima-big-event:login:user:{userId}:device:{deviceId}
            String token = String.format(RedisConstants.USER_LOGIN_KEY,user.getId(),deviceID);

            //安全信息过滤
            User safeUser = new User();
            safeUser.setId(user.getId());
            safeUser.setUsername(user.getUsername());
            String jsonString = JSONHelper.writeValue(safeUser);


            //过期时间为12小时
            //key为token,value为json 字符串
            stringRedisTemplate.opsForValue().set(token,jsonString,RedisConstants.USER_LOGIN_EXPIRE, TimeUnit.MINUTES);

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

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@URL String avatarUrl){
        int rows = userService.updateAvatar(avatarUrl);
        return rows>0?Result.success():Result.error("因未知原因,更新用户头像失败");
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Device-ID") String deviceID) throws JsonProcessingException {

        //获取设备信息
        if(deviceID==null || deviceID.isEmpty()){
            throw new RuntimeException("设备id不能为空");
        }


        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("存在密码为空,请重新输入");
        }

        if(!newPwd.equals(rePwd)){
            return Result.error("新密码和重复密码不一致");
        }

        String regex = "^[a-zA-Z0-9_.]{5,16}$";
        if(!newPwd.matches(regex)){
            return Result.error("新密码不能满足只包含字母、数字、下划线和点，且长度在5到16之间的要求");
        }

        Map<String,Object> claims= ThreadLocalUtil.get();
        String username = (String) claims.get("username");//注意username是唯一的
        User user = userService.findByUsername(username);//注意这里的user必然不为空
        if(!Md5Util.getMD5String(oldPwd).equals(user.getPassword())){//密码如果不匹配
            return Result.error("原密码不正确!");
        }

        int rows = userService.updatePwd(newPwd);
        if(rows==0){
            return Result.error("因未知原因,更新密码失败");
        }

        //更新user信息
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        String jsonString = JSONHelper.writeValue(safeUser);


        //删除该用户所有旧的token
        //userPattern为 itheima-big-event:login:user:{userid}:device:*
        String userPattern = String.format(RedisConstants.USER_LOGIN_KEY,user.getId(),"*");
        Set<String> keys = stringRedisTemplate.keys(userPattern);
        stringRedisTemplate.delete(keys);

        //生成一个 新的token
        String newToken = String.format(RedisConstants.USER_LOGIN_KEY,user.getId(),deviceID);
        stringRedisTemplate.opsForValue().set(newToken,jsonString);

        //返回新的token
        return Result.success(newToken);
    }



    @PostMapping("logout")
    public Result logout(@RequestHeader("Device-ID") String deviceID)  {

        //获取设备信息
        if(deviceID==null || deviceID.isEmpty()){
            throw new RuntimeException("设备id不能为空");
        }

        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer userid = (Integer) claims.get("id");
        String token = String.format(RedisConstants.USER_LOGIN_KEY,userid,deviceID);
        stringRedisTemplate.delete(token);//删除当前用户的当前设备的id

        return Result.success();
    }
}
