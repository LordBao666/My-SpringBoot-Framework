package com.lordbao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Lord_Bao
 * @Date 2024/8/17 21:07
 * @Version 1.0
 */
/* This @SpringBootApplication  annotation has three functions:
*  1   @SpringBootConfiguration , it means it is a configuration,we can
*      add @Bean in Main directly.
*  2   @EnableAutoConfiguration , it is like   getRootConfigClasses and
*      getWebServletClasses of MyWebInitializer in SSM integration, which can
*      automatically add  classes annotated with @Configuration.
*  3   @ComponentScan, it can scan the components of the same package and sub
*      packages of Main.
*
* */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
