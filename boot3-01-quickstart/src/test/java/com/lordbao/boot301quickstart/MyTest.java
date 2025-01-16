package com.lordbao.boot301quickstart;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.lordbao.entity.Favorite;
import com.lordbao.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/1 22:10
 * @Version 1.0
 */
public class MyTest {

    @Test
    public void testYaml() throws JsonProcessingException {
        //禁用文档开始的分割符 ---
        YAMLFactory yamlFactory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        Person p = new Person();
        p.setId(2L);
        p.setUserName("kafak");
        p.setEmail("kafak@gmail.com");
        p.setAge(13);

        List<String> likes = new ArrayList<>();
        likes.add("sing");
        likes.add("dance");
        likes.add("rap");
        p.setLikes(likes);

        Favorite favorite = new Favorite();
        favorite.setFavoriteStar("JackieChan");
        favorite.setFavoriteMovie("TheAvengers");
        favorite.setFavoriteBook("TheJourneyToWest");

        p.setFavorite(favorite);

        String yamlStr = mapper.writeValueAsString(p);
        System.out.println(yamlStr);

    }
}
