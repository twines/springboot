package com.hanyun.lee.Controller;

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
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
