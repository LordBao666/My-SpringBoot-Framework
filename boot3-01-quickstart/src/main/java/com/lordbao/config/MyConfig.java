package com.lordbao.config;

import com.lordbao.utils.converter.MyPropertiesConverter;
import com.lordbao.utils.converter.MyYamlConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration //这是一个配置类,给容器中放一个 WebMvcConfigurer 组件，就能自定义底层
public class MyConfig {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            //静态资源处理配置
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/static/**")
//                        .addResourceLocations("classpath:/a/", "classpath:/b/")
//                        .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS));
//            }


            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyYamlConverter());
                converters.add(new MyPropertiesConverter());
            }
        };
    }

}