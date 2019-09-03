package com.twins.lee.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("role_id")
    private Long roleId;
}
