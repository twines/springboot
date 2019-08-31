package com.hanyun.lee.service;

import com.hanyun.lee.entity.User;
import com.hanyun.lee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
        return userMapper.getUserById(id);
    }

}
