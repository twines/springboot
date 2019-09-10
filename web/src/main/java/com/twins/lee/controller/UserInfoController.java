package com.twins.lee.controller;

import com.twins.lee.entity.Improv;
import com.twins.lee.service.impl.ImprovService;
import com.twins.lee.utilites.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller

public class UserInfoController {
    @Autowired
    ImprovService improvService;
    @GetMapping("/user/info")
    public String userInfo(Model model) {
        Map userInfo = Utility.userInfo();
        Improv improv = improvService.UserImproveResultById(Utility.userId());
        if (improv == null) {
            return "redirect:/user/improv";
        }
        userInfo.put("improv", improv);
        model.addAttribute("userInfo", userInfo);
        return "user/info";
    }
}
