package com.twins.lee.controller;

import com.twins.lee.entity.Improv;
import com.twins.lee.service.impl.ImprovService;
import com.twins.lee.utilites.ShiroUtility;
import com.twins.lee.utilites.Utility;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    ImprovService improvService;
    @Value("${environment}")
    private String environment;

    @RequestMapping("/")
    public String index(Model model) throws UnsupportedEncodingException {
        System.out.print(this.environment);
        Subject subject = SecurityUtils.getSubject();
        Object value = subject.getPrincipal();
//        if (ShiroUtility.isLogin()) {
//            Map<String, String> userInfo = ShiroUtility.casResut();
//            Long userId = Long.valueOf(userInfo.get("id"));
//            Improv improv = improvService.UserImproveResultById(userId);
////            if (improv == null) {
////                return "/";
////            }
//            if (improv ==null || improv.getState() == Improv.State.NeededInproved) {
//                return "redirect:/user/improv";
//            }
//        }

        Map<String, Object> userInfo = new LinkedHashMap<>();
        if (ShiroUtility.isLogin()) {
            Map<String, String> info = ShiroUtility.casResut();

            userInfo.putAll(info);
        }
        Improv improv = improvService.UserImproveResultById(Utility.userId());
        if (improv == null) {

            userInfo.put("state", Improv.State.NeededInproved);
        }else{
            userInfo.put("state", improv.getState());
        }


        model.addAttribute("userInfo", userInfo);
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
