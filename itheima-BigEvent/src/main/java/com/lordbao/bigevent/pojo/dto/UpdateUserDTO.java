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
public class UpdateUserDTO {
    @NotNull
    private Integer id;//主键ID


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
