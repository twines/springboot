package com.twins.lee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.twins.lee.entity.newEntity.Company;
import com.twins.lee.mapper.CompanyMapper;
import com.twins.lee.request.CompanyRequest;
import com.twins.lee.response.Response;
import com.twins.lee.utilites.Utility;
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
    public String improvCompany(@RequestParam(defaultValue = "true",required = false,value = "redirect") boolean redirect) {
//不能重复的去完善企业信息，可以走修改功能
        Company company = companyMapper.selectByUserId(Utility.userId());
        if (company != null && company.getStatus() == Company.Finished) {
            if (redirect==false) {
                return "company/company";
            }
            return "redirect:/";
        }
        return "company/company";
    }

    @PostMapping("/center/improv")
    @ResponseBody
    public Response doImprovCompany(@RequestParam Map<String, Object> params, @RequestParam(value = "assetProof[]") String[] assetProof) {
        String json = JSONObject.toJSONString(params, SerializerFeature.WriteMapNullValue);

        CompanyRequest companyRequest = JSON.parseObject(json, CompanyRequest.class);

        companyRequest.setUserId(Utility.userId());
        Company innerCompany = companyMapper.selectByUserId(companyRequest.getUserId());
        if (innerCompany != null) {

            return Response.error("用户已经存在不能重复提交");
        }


        Company company = new Company(Utility.userId());
        company.setUserName(companyRequest.getUserName());
        company.setCreditCode(companyRequest.getCreditCode());
        company.setIdNumber(companyRequest.getIdNumber());
        company.setBankNumber(companyRequest.getBankNumber());
        company.setMobile(companyRequest.getMobile());
        company.setCardA(companyRequest.getCardA());
        company.setCardB(companyRequest.getCardB());
        company.setBusinessLicense(companyRequest.getBusinessLicense());
        //更改企业状态
        company.setStatus(1);

        StringBuffer stringBuffer = new StringBuffer();
        for (int index = 0; index < assetProof.length; index++) {
            if (index < assetProof.length - 1) {
                stringBuffer.append(assetProof[index] + ",");
            } else {
                stringBuffer.append(assetProof[index]);
            }
        }
        company.setAssetProof(stringBuffer.toString());

        int result = companyMapper.insert(company);
        if (result > 0) {
            innerCompany = companyMapper.selectByUserId(companyRequest.getUserId());
        } else {
            return Response.error("数据保存出错");
        }
        return Response.success(innerCompany);
    }

}
