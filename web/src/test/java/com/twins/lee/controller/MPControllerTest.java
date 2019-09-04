package com.twins.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twins.lee.entity.User;
import com.twins.lee.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Authoe hanyun
 * @Email 1355081829@qq.com
 * @Date 2019/9/3
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MPControllerTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectOn() {
        userMapper.selectOne(new QueryWrapper<User>().eq("id", 1));
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setRealName("1");
        user.setPassword("a");
        user.setUserName("v");
        int userId = userMapper.insert(user);
        System.out.println(userId);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2);
        user.setUserName("youyou");
        userMapper.updateById(user);
    }

    @Test
    public void updateAllUser() {
        User user = new User();
        user.setRealName("hhhh");
        userMapper.update(user, new QueryWrapper<User>());
    }
}