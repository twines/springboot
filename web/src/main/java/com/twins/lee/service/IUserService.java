package com.twins.lee.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twins.lee.entity.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService extends IService<User> {
    User getUserById(int id);
    User getUserByName(String userName);

    IPage<User> selectUserByPage(Page page, @Param("id") Integer state);

}
