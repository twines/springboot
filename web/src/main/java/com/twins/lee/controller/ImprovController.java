package com.twins.lee.controller;

import com.twins.lee.entity.Improv;
import com.twins.lee.model.BaseModel;
import com.twins.lee.service.IImprovService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class ImprovController {
    @Resource
    IImprovService improvService;

    @GetMapping("/improv")
    String index(){
        return "improving";
    }

    @PostMapping("/doImpov")
    @ResponseBody
    BaseModel improv(@RequestParam("code") String code,
                  @RequestParam("code-part") String codePart,
                  @RequestParam("name-part") String namePart,
                  @RequestParam("name") String name) {
        Subject subject = SecurityUtils.getSubject();
        List<Object> principals = subject.getPrincipals().asList();
        Map<String, String> user = (Map<String, String>) principals.get(principals.size() - 1);

        String result = code + "\n" + codePart + "\n" + name + "\n" + namePart;
        System.out.println();
        Improv improv = new Improv();
        improv.setCode(code);
        improv.setCodeUrl(codePart);
        improv.setName(name);
        improv.setNameUrl(namePart);
        improv.setId(user.get("id"));
        int rev = improvService.save(improv);
        BaseModel baseModel = null;
        if (rev > 0) {
             baseModel = new BaseModel(BaseModel.Success, result, "success");

        }else{
             baseModel = new BaseModel(BaseModel.Failed, result, "数据保存失败");

        }

        return baseModel;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public BaseModel upload(@RequestParam("file") MultipartFile file) {


        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        BaseModel baseResult = null;
        try {

            // 文件上传路径
            String filePath = null;
            filePath = ResourceUtils.getFile("classpath:").getAbsolutePath();

            // 解决中文问题，liunx下中文路径，图片显示问题
            fileName = UUID.randomUUID() + suffixName;
            String fileUrl = "/tmp/" + fileName;
            filePath = filePath +"/static"+ fileUrl;
            File dest = new File(filePath);

            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);

            baseResult = new BaseModel(BaseModel.Success, fileUrl, "success");
         } catch (Exception e) {
            baseResult = new BaseModel(BaseModel.Exception, e, "success");

        }
        return baseResult;
    }
}
