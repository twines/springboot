package com.twins.lee.service.impl;

import com.twins.lee.entity.Role;
import com.twins.lee.mapper.RoleMapper;
import com.twins.lee.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRoleById(Long id) {
        Role role = roleMapper.getRoleById(id);
        return role;
    }
}
