package com.twins.lee.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twins.lee.entity.User;
import com.twins.lee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Value("${environment}")
    private String environment;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public String index() {
//        System.out.print(this.environment);
        System.out.print(iUserService.getUserById(1));
        return "login";
    }

    @RequestMapping("/doLogin")
    public String login(HttpServletRequest request, String account, String password) {
        User user = iUserService.getUserById(1);
        return "";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public IPage<User> useList(@RequestParam(value = "page", required = false, defaultValue = "1") long p) {
        Page page = new Page<User>(p, 2);
        return iUserService.selectUserByPage(page, 1);
    }
}
