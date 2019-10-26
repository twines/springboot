package com.twins.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserHomeController {
    @RequestMapping("/home")
    public String home(Model model) {
        return "user/home";
    }
}
