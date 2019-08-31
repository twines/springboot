package com.hanyun.lee.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Value("${environment}")
    private String environment;

    @RequestMapping("/login")
    public String index() {
        System.out.print(this.environment);
        return "login";
    }
}
