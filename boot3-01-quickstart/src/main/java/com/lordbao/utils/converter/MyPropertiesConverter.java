package com.lordbao.utils.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author Lord_Bao
 * @Date 2024/10/10 11:23
 * @Version 1.0
 */
public class MyPropertiesConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper;
    public MyPropertiesConverter(){
        super(new MediaType("text", "properties", StandardCharsets.UTF_8));
        this.objectMapper = new ObjectMapper(new JavaPropsFactory()); // 使用Jackson的ObjectMapper
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;//返回true, 表示MyPropertiesConverter支持所有引用类型
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object returnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {


        try (OutputStream body = outputMessage.getBody()) {
           objectMapper.writeValue(body,returnValue);
        }
    }
}
