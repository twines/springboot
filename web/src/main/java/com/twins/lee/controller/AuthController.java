package com.twins.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @GetMapping("/auth/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("auth/index");
        return modelAndView;
    }

    @PostMapping("/auth/doAuth")
    @ResponseBody
    public Map<String, Object> doAuth() {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
