package com.twins.lee.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twins.lee.entity.Permission;
import com.twins.lee.entity.Role;
import com.twins.lee.entity.User;
import com.twins.lee.mapper.RoleMapper;
import com.twins.lee.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;

    /*
    授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //默认使用null不进行权限控制
        for (Role role : user.getRoles()) {
            authorizationInfo.addRole(role.getRole());
            List<Permission> permissions = roleMapper.getRoleById(role.getId()).getPermissions();
            for (Permission permission : permissions) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*
    认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        if (user == null) {
            return null;
        }
        user = userMapper.getUserById(user.getId());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), new MyByteSource(user.getUserName()), getName());

        return authenticationInfo;
    }
}
