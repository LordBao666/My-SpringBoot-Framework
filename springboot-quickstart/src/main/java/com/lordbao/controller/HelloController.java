package com.lordbao.controller;

import com.lordbao.pojo.User;
import com.lordbao.pojo.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/17 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("springboot")
public class HelloController {

    @Autowired
    private User user;
    @Autowired
    private User2 user2;

    @GetMapping("hello")
    public String hello(){
        return "Hello SpringBoot 3";
    }

    @GetMapping("user")
    public List<Object> getUsers( ){
        List<Object> list= new ArrayList<>();
        list.add(user);
        list.add(user2);
        return list;
    }
}
