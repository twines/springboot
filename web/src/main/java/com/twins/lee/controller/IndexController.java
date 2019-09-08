package com.twins.lee.controller;

import com.twins.lee.entity.Improv;
import com.twins.lee.service.impl.ImprovService;
import com.twins.lee.utilites.ShiroUtility;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    ImprovService improvService;
    @Value("${environment}")
    private String environment;

    @RequestMapping("/")
    public String index() {
        System.out.print(this.environment);
        Subject subject = SecurityUtils.getSubject();
        Object value = subject.getPrincipal();
        if (ShiroUtility.isLogin()) {
            Map<String, String> userInfo = ShiroUtility.casResut();
            Long userId = Long.valueOf(userInfo.get("id"));
            if (improvService.UserImproveResultById(userId) == Improv.State.NeededInproved) {
                return "redirect:/user/improv";
            }
        }

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

//    @RequiresRoles(value = {"admin","vip"}, logical = Logical.OR)
    @RequestMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }
}
