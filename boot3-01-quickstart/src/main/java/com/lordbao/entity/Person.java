package com.lordbao.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/10/7 19:52
 * @Version 1.0
 */
@JacksonXmlRootElement
@Data
public class Person {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
    private List<String> likes;
    private Favorite favorite;
}
