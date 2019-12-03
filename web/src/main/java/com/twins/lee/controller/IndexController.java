package com.twins.lee.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.twins.lee.entity.Improv;
import com.twins.lee.entity.newEntity.DashboardIndex;
import com.twins.lee.mapper.DashboardIndexMapper;
import com.twins.lee.service.impl.ImprovService;
import com.twins.lee.utilites.ShiroUtility;
import com.twins.lee.utilites.Utility;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    DashboardIndexMapper dashboardIndexMapper;
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
//        Improv improv = improvService.UserImproveResultById(Utility.userId());
//        if (improv == null) {
//
//            userInfo.put("state", Improv.State.NeededInproved);
//        }else{
//            userInfo.put("state", improv.getState());
//        }
//
//
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
    public String dashboard(Model model) {
        Long userId = Utility.userId();
        List<DashboardIndex> values = dashboardIndexMapper.dashboardIndex(userId);
        Map results = new HashMap();
//        1货贷；2汇贷；3税贷；4结汇；5退税
        for (DashboardIndex dashboardIndex : values) {
            int type = dashboardIndex.getType();
            switch (type) {
                case 1:
                    results.put("loanGoods", dashboardIndex);//货贷
                    break;
                case 2:
                    results.put("exchange", dashboardIndex);//结汇
                    break;
                case 3:
                    results.put("drawback", dashboardIndex);//退税
                    break;

            }
        }
        model.addAttribute("results", results);

        return "pages/dashboard/index";
    }

    @GetMapping("/auth")
    public String auth() {
        return "pages/auth/info";
    }

    @GetMapping("/shipping/add")
    public String shippingAdd() {
        return "pages/shipping/add";
    }

    @GetMapping("/shipping/addCredit")
    public String shippingAddCredit() {
        return "pages/shipping/addCredit";
    }

    @GetMapping("/shipping/addTax")
    public String shippingAddTax() {
        return "pages/shipping/addTax";
    }

    @GetMapping("/exchange/add")
    public String exchangeAddTax() {
        return "pages/exchange/add";
    }

    @GetMapping("/drawback/add")
    public String drawbackAdd() {
        return "pages/drawback/add";
    }

    @GetMapping("/auth/list")
    public String authList() {
        return "pages/auth/list";
    }

    @GetMapping("/shipping/list")
    public String shippingList() {
        return "pages/shipping/list";
    }

}
