package com.hanyun.lee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanyun.lee.entity.User;

public interface UserMapper extends BaseMapper<User> {
    User getUserById(int id);
}
