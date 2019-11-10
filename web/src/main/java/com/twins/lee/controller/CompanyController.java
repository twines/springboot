package com.twins.lee.controller;

import com.alibaba.fastjson.JSONObject;
import com.twins.lee.mapper.CompanyMapper;
import com.twins.lee.request.CompanyRequest;
import com.twins.lee.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    CompanyMapper companyMapper;
    @GetMapping("/center/improv")
    public String improvCompany() {


        return "company/company";
    }

    @PostMapping("/center/improv")
    @ResponseBody
    public Response doImprovCompany(@RequestParam Map<String, Object> mapRequest) {
        CompanyRequest companyRequest = JSONObject.parseObject(JSONObject.toJSONString(mapRequest), CompanyRequest.class);

        return Response.success(companyRequest);
     }




}
