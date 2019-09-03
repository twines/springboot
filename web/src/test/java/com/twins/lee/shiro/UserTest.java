package com.twins.lee.shiro;

import com.twins.lee.entity.Role;
import com.twins.lee.entity.User;
import com.twins.lee.mapper.RoleMapper;
import com.twins.lee.mapper.UserMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void testUser() {

        User user = userMapper.getUserById(1);
        TestCase.assertNotNull("获取user失败：1", user);
        TestCase.assertTrue("user下面无角色", user.getRoles().size() >= 0);

    }
}
