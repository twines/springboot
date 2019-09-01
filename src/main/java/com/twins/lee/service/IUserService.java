package com.twins.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twins.lee.entity.User;

public interface IUserService extends IService<User> {
    User getUserById(int id);
}
