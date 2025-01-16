package com.lordbao.utils.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author Lord_Bao
 * @Date 2024/10/10 11:23
 * @Version 1.0
 */
public class MyYamlConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper;
    public MyYamlConverter(){
        super(new MediaType("text","yaml", StandardCharsets.UTF_8));
        YAMLFactory yamlFactory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(yamlFactory);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;//返回true, 表示MyYamlConverter支持所有引用类型
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object methodReturnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try(OutputStream body = outputMessage.getBody()){
            objectMapper.writeValue(body,methodReturnValue);
        }
    }
}
