package com.hanyun.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanyun.lee.entity.User;

public interface IUserService extends IService<User> {
    User getUserById(int id);
}
