package com.twins.lee.controller;

import com.twins.lee.entity.User;
import com.twins.lee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
