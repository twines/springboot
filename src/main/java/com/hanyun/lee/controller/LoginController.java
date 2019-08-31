package com.hanyun.lee.controller;

import com.hanyun.lee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Value("${environment}")
    private String environment;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/login")
    public String index() {
//        System.out.print(this.environment);
        System.out.print(iUserService.getUserById(1));
        return "login";
    }
}
