package com.twins.lee.controller;

import com.twins.lee.request.AuthRequest;
import com.twins.lee.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("auth/index");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Response<Void> auth(AuthRequest authRequest) {

        Response response = Response.success();

        return response;
    }
}
