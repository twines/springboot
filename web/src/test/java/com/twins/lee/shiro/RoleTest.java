package com.twins.lee.shiro;


import com.twins.lee.entity.Role;
import com.twins.lee.entity.User;
import com.twins.lee.mapper.RoleMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {
    @Resource
    RoleMapper roleMapper;
    @Test
    public void testUser() {
        Role role = roleMapper.getRoleById(1L);
        TestCase.assertNotNull("获取用户错错，或者用户不存在", role);
        TestCase.assertTrue("获取用户失败", role.getId() == 1L);

    }
}
