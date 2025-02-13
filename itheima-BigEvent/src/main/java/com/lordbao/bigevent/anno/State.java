package com.lordbao.bigevent.anno;


import com.lordbao.bigevent.anno.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author Lord_Bao
 * @Date 2025/2/13 9:49
 * @Version 1.0
 */
@Documented //元注解,表示会记录在帮助文档中
@Target({ElementType.FIELD})//元组解,表明该注解出现在数据域上
@Retention(RetentionPolicy.RUNTIME) //元组解,表明该注解保留在什么阶段,这里是运行阶段
@Constraint(validatedBy = {StateValidation.class}) //校验规则由什么类给出，必然上面是我们自定义的StateValidation给出
public @interface State {
    String message() default "{state参数只能是草稿或已发布}";

    //指定分组
    Class<?>[] groups() default {};

    //负载  获取到State注解的附加信息
    //一般这个属性都没有用
    Class<? extends Payload>[] payload() default {};
}

