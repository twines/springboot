package com.twins.lee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.twins.lee.entity.newEntity.Company;
import com.twins.lee.entity.newEntity.Shipping;
import com.twins.lee.mapper.CompanyMapper;
import com.twins.lee.mapper.ShippingMapper;
import com.twins.lee.request.Common;
import com.twins.lee.response.Response;
import com.twins.lee.utilites.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/load")
public class LoanGoodsController {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    ShippingMapper shippingMapper;

    @RequestMapping("/index")
    public String index() {
        Company company = companyMapper.selectByUserId(Utility.userId());
        if (company != null && company.getStatus() == Company.Finished) {//资料已经完善啦
            return "pages/shipping/add";
        }
        return "redirect:/company/center/improv";
    }

    @PostMapping("/index")
    public Response doLoan(@RequestParam Map<String, Object> params) {
        Long userId = Utility.userId();
        Shipping shipping = JSON.parseObject(JSONObject.toJSONString(params), Shipping.class);
        shipping.setUserId(userId);
        shipping.setType(Common.LoadType);

        int result = shippingMapper.insert(shipping);
        if (result > 0) {//创建货贷唯一id
            Long shippingId = shipping.getId();
//TODO 补全数据

        } else {//插入失败

        }

        return Response.success();
    }
}
