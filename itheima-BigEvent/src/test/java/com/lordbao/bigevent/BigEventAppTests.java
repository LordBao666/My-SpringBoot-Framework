package com.lordbao.bigevent;

import com.lordbao.bigevent.config.OSSConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BigEventAppTests {


    @Autowired
    private OSSConfigProperties properties;

    @Test
    public void fileLoad(){
        System.out.println(properties.getAccessKey());
        System.out.println(properties.getSecretKey());
        System.out.println(properties.getBucket());
    }
}
