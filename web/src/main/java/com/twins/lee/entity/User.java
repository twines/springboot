package com.twins.lee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer id;
    @TableField("user_name")
    private String userName;
    @TableField("password")
    private String password;
    @TableField("real_name")
    private String realName;
    @TableField(exist = false)
    private    List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
