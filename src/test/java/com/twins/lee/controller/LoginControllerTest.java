package com.twins.lee.controller;

import com.twins.lee.entity.User;
import com.twins.lee.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void index() {
        System.out.print(iUserService.getUserById(1));
    }

    @Test
    public void login() {
        User user = iUserService.getUserById(1);
        if (user != null) {
            redisTemplate.opsForValue().set("loginUser:" + user.getId(), "11111111");
        } else {
        }

    }
}