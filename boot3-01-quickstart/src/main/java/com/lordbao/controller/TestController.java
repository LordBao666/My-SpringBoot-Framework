package com.lordbao.controller;


import com.lordbao.entity.Favorite;
import com.lordbao.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/10/7 19:50
 * @Version 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("person")
    public Person testFormat() {
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
        return p;
    }

}
