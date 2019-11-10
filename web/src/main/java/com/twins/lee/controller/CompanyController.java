package com.twins.lee.controller;

import com.twins.lee.entity.newEntity.Company;
import com.twins.lee.mapper.CompanyMapper;
import com.twins.lee.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    CompanyMapper companyMapper;
    @GetMapping("/center/improv")
    public String improvCompany( @ModelAttribute("query") Company company) {


        return "company/company";
    }

    @PostMapping("/center/improv")
    @ResponseBody
    public Response doImprovCompany( @ModelAttribute("company") Company company) {
        if (company == null || company.getUserId() == 0) {

            return Response.error(0,"用户不存在请在主系统注册");
        }
        return Response.success(company);
    }

    @ModelAttribute("query")
    public Company queryCompany(@RequestParam(value = "user_id") Integer userId) {
        Company query = companyMapper.selectByUserId(userId);
        return query;

    }

    @ModelAttribute("company")
    public Company getCompany(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
                              @RequestParam(value = "user_id") Integer userId,//必须存在
                              @RequestParam(value = "credit_code", required = false) String creditCode,
                              @RequestParam(value = "user_name", required = false) String userName,
                              @RequestParam(value = "id_number", required = false) String idNumber,
                              @RequestParam(value = "bank_number", required = false) String bankNumber,
                              @RequestParam( required = false) String mobile,
                              @RequestParam(value = "card_a", required = false) String cardA,
                              @RequestParam(value = "card_b", required = false) String cardB,
                              @RequestParam(value = "business_license", required = false) String businessLicense,
                              @RequestParam(value = "asset_proof", required = false) String assetProof,
                              @RequestParam(defaultValue = "0", required = false) int status) {
        Company company = new Company(id,
                userId,
                creditCode,
                userName,
                idNumber,
                bankNumber,
                mobile,
                cardA,
                cardB,
                businessLicense,
                assetProof,
                status);


        Company innerCompany = companyMapper.selectByUserId(company.getUserId());
        int result = 0;
        if (company.getId() > 0) {
            return companyMapper.selectById(company.getId());
        }
        if (innerCompany == null) {
            result =   companyMapper.insert(company);
        }
        if (result > 0) {
            innerCompany = companyMapper.selectByUserId(company.getUserId());
        }

        return innerCompany;
    }

}
