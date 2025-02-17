package com.lordbao.bigevent.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author Lord_Bao
 * @Date 2025/2/16 13:15
 * @Version 1.0
 */
public class JSONHelper {
    //SpringMVC场景依赖自带的,用于处理json的jackson工具中的ObjectMapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Object readValue(String jsonString,Class clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonString,clazz);
    }

    public static String writeValue(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
