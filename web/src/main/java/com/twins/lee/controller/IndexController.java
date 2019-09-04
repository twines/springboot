package com.twins.lee.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Value("${environment}")
    private String environment;

    @RequestMapping("/")
    public String index() {
        System.out.print(this.environment);
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        System.out.print(this.environment);
        return "home";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }

    @RequiresRoles(value = {"admin","vip"}, logical = Logical.OR)
    @RequestMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }
}
