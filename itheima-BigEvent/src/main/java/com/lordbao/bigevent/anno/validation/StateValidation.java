package com.lordbao.bigevent.anno.validation;


import com.lordbao.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author Lord_Bao
 * @Date 2025/2/13 9:52
 * @Version 1.0
 */
//ConstraintValidator<A extends Annotation, T>  A表示给那个注解提供校验规则
//T表示校验的类型是什么
public class StateValidation implements ConstraintValidator<State,String>{

    //s 就是将来校验的对象
    //返回true,表示校验成功,返回false,表示校验失败
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return false;
        }

        return s.equals("已发布") || s.equals("草稿");
    }
}
