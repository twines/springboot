package com.twins.lee.controller;

import com.twins.lee.entity.AssetBill;
import com.twins.lee.mapper.AssetBillMapper;
import com.twins.lee.request.AuthRequest;
import com.twins.lee.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AssetBillMapper assetBillMapper;


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
    public Map<String, String> doAuth(@RequestParam Map<String, String> person, @RequestParam(value = "balanceBill[]", required = false) String[] balanceBill) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(person);
        if (balanceBill.length > 0) {
            for (String bill : balanceBill) {
                AssetBill assetBill = new AssetBill();
                assetBill.setUserId(1);
                assetBill.setAssetBill(bill);
                assetBillMapper.insert(assetBill);
            }
        }
        return person;
    }
}
