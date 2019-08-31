package com.hanyun.lee.mapper;

import com.hanyun.lee.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUserById(int id);
}
