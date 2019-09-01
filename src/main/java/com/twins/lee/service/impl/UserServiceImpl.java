package com.twins.lee.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twins.lee.entity.User;
import com.twins.lee.mapper.UserMapper;
import com.twins.lee.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getUserById(int id) {
        return baseMapper.getUserById(id);
    }

    @Override
    public IPage<User> selectUserByPage(Page page, Integer state) {
        return baseMapper.selectUserByPage(page, state);
    }
}
