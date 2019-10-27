package com.twins.lee.controller;

import com.twins.lee.request.AuthRequest;
import com.twins.lee.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("auth/index");
        return modelAndView;
    }

    @RequestMapping(value = "/doUserAuth", method = RequestMethod.POST)
    public Response<Void> auth(AuthRequest authRequest) {

        Response response = Response.success();

        return response;
    }

    @PostMapping("/doAuth")
    @ResponseBody
    public Map<String, Object> doAuth() {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
