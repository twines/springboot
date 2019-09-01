package com.twins.lee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twins.lee.entity.User;

public interface UserMapper extends BaseMapper<User> {
    User getUserById(int id);
}
