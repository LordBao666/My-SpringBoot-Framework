package com.lordbao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Boot301QuickstartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Boot301QuickstartApplication.class, args);

        //打印总的组件个数
        //System.out.println(context.getBeanDefinitionCount());

        //得到所有组件姓名
//        String[] names = context.getBeanDefinitionNames();
//        for(String name : names){
//            System.out.println(name);
//        }

         //得到特定类型的所有组件姓名
//        String[] catNames = context.getBeanNamesForType(Cat.class);
//        System.out.println(Arrays.toString(catNames));
//        String[] dogNames = context.getBeanNamesForType(Dog.class);
//        System.out.println(Arrays.toString(dogNames));
//        String[] userNames = context.getBeanNamesForType(User.class);
//        System.out.println(Arrays.toString(userNames));


    }

}
