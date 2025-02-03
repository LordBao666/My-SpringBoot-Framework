package com.lordbao.bigevent.pojo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Lord_Bao
 * @Date 2025/2/3 12:09
 * @Version 1.0
 */
@Data
public class RegisterUserDTO {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名只允许字母、数字和下划线，且长度在5到16之间")
    private String username;//用户名
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_.]{5,16}$", message = "密码可以包含字母、数字、下划线和点，且长度在5到16之间")
    private String password;//密码

    @NotEmpty //nickname不能为空,或空白串 "      "
    @Pattern(regexp = "^\\S{1,10}$",message = "昵称为1 到 10 个非空白字符") //匹配 1 到 10 个非空白字符 的字符串,因此实际上上面的NotEmpty可以不要
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
