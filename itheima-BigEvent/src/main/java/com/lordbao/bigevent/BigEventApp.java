package com.lordbao.bigevent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lordbao.bigevent.mapper")
public class BigEventApp {

    public static void main(String[] args) {
        SpringApplication.run(BigEventApp.class, args);
    }

}
