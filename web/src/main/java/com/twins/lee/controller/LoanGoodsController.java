package com.twins.lee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.twins.lee.entity.newEntity.Company;
import com.twins.lee.entity.newEntity.Shipping;
import com.twins.lee.entity.newEntity.ShippingImage;
import com.twins.lee.mapper.CompanyMapper;
import com.twins.lee.mapper.ShippingImageMapper;
import com.twins.lee.mapper.ShippingMapper;
import com.twins.lee.request.Common;
import com.twins.lee.response.Response;
import com.twins.lee.utilites.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loan")
public class LoanGoodsController {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    ShippingMapper shippingMapper;
    @Autowired
    ShippingImageMapper shippingImageMapper;

    @GetMapping("/index")
    public String index() {
        Company company = companyMapper.selectByUserId(Utility.userId());
        if (company != null && company.getStatus() == Company.Finished) {//资料已经完善啦
            return "pages/shipping/add";
        }
        return "redirect:/company/center/improv";
    }

    @PostMapping("/index")
    @ResponseBody
    public Response doLoan(@RequestParam(name = "taxBill[]") List<String> taxBill,
                           @RequestParam(name = "entryBill[]") List<String> entryBill,
                           @RequestParam(name = "logisticsBill[]") List<String> logisticsBill,
                           @RequestParam(name = "tradeBill[]") List<String> tradeBills) {

        Long userId = Utility.userId();
        Shipping shipping = new Shipping();
        shipping.setUserId(userId);
        shipping.setType(Common.LoadType);

        int result = shippingMapper.insert(shipping);
        if (result > 0) {//创建货贷唯一id
            Long shippingId = shipping.getId();
            ShippingImage shippingImage = new ShippingImage();
            shippingImage.setShippingId(new Long(shipping.getId()));
            shippingImage.setUserId(Utility.userId());
            shippingImage.setTaxBill(String.join(",", taxBill));
            shippingImage.setCustomsBill(String.join(",", entryBill));
            shippingImage.setLogisticsBill(String.join(",", logisticsBill));
            shippingImage.setTradeContract(String.join(",",tradeBills));
            result = shippingImageMapper.insert(shippingImage);
            if (result > 0) {
                return Response.success(shippingImage);
            }else{
                return Response.error("货贷申请异常");
            }

        } else {//插入失败
            return Response.error("数据库异常");
        }

    }
}
