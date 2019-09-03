package com.twins.lee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twins.lee.entity.Role;

public interface RoleMapper extends BaseMapper<Role> {
    Role getRoleById(Long id);
}
