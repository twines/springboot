package com.hanyun.lee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanyun.lee.entity.User;
import com.hanyun.lee.mapper.UserMapper;
import com.hanyun.lee.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getUserById(int id) {
        return baseMapper.getUserById(id);
    }
}
