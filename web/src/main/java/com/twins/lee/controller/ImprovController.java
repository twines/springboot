package com.twins.lee.controller;

import com.twins.lee.entity.Improv;
import com.twins.lee.response.Response;
import com.twins.lee.service.IImprovService;
import com.twins.lee.utilites.ShiroUtility;
import com.twins.lee.utilites.Utility;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class ImprovController {
    @Resource
    IImprovService improvService;
    @Value("${twins.uploadFolder}")
    private String docLocation;

    @GetMapping("/improv")
    String index() {

        Subject subject = SecurityUtils.getSubject();
        Object value = subject.getPrincipal();
        if (ShiroUtility.isLogin()) {
            Map<String, String> userInfo = ShiroUtility.casResut();
            Long userId = Long.valueOf(userInfo.get("id"));
            Improv improv = improvService.UserImproveResultById(userId);
            if (improv != null && improv.getState() != Improv.State.NeededInproved) {
                return "redirect:/";
            }
        }
        return "improving";
    }

    @PostMapping("/doImpov")
    @ResponseBody
    Response improv(@RequestParam("code") String code,
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
        improv.setId(Long.parseLong(user.get("id")));
        improv.setState(Improv.State.Improved);
        int rev = improvService.save(improv);
        Response baseModel = null;

//        if (ocrCard(improv.getNameUrl(), improv.getCodeUrl(),
//                improv.getCode(), improv.getName()) == false) {
//            baseModel = new BaseModel(BaseModel.Failed, result, "法人姓名或企业营业执照不统一");
//            return baseModel;
//        }
        if (rev > 0) {
            baseModel = new Response(Response.Success, result, "success");

        } else {
            baseModel = new Response(Response.Failed, result, "数据保存失败");

        }


        return baseModel;
    }


    protected boolean ocrCard(String idCardPath, String cardPath,
                              String inputCode, String inputName) {
        String idCardOcrResult = ocrReco(idCardPath);

        Pattern pattern = Pattern.compile("名[^\\x00-\\xff]{2,4}");
        Matcher matcher = pattern.matcher(idCardOcrResult);
        String idName = null;
        if (matcher.find()) {
            idName = matcher.group();
        }
        idName = idName.replace("名", "");

        //统一码
        String code = null;
        //法人
        String fName = null;
        String cardOcrResult = ocrReco(cardPath);
        pattern = Pattern.compile("社会信用代码+\\d+p");
        matcher = pattern.matcher(cardOcrResult);
        if (matcher.find()) {
            code = matcher.group();
            code = code.replace("社会信用代码", "")
                    .replace("p", "");
        }
        matcher = Pattern.compile("D法定代表人[^\\x00-\\xff]{2,4},C").matcher(cardOcrResult);
        if (matcher.find()) {
            fName = matcher.group();
            fName = fName.replace("D法定代表人", "")
                    .replace(",C", "");
        }
        if ((fName.equals(idName) && idName.equals(inputName)) && code.equals(inputCode)) {
            return true;
        }
        return false;
    }

    protected String ocrReco(String imgPath) {
        String sysPath = fileDestPath();
        String tranPath = sysPath;
        String destPath = sysPath + "/static" + imgPath;
        if (docLocation != null) {
            destPath = docLocation + imgPath;
        }
        File imageFile = new File(destPath);
        ITesseract instance = new Tesseract();
        instance.setDatapath(tranPath);
        instance.setLanguage("chi_sim");
//        instance.setLanguage("eng");

        try {
            String result = instance.doOCR(imageFile).replace(" ", "");

            return result;
        } catch (TesseractException e) {
            return null;
        }
    }

    private String fileDestPath() {
        String filePath = null;
        try {
            filePath = ResourceUtils.getFile("classpath:").getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return filePath;
    }

    @Deprecated
    @RequestMapping("/upload")
    @ResponseBody
    public Response upload(@RequestParam("file") MultipartFile file) {


        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        Response baseResult = null;
        try {

            // 文件上传路径
            String filePath = null;
            filePath = fileDestPath();
            if (docLocation != null) {
                filePath = docLocation;
            }
            //用户目录
            String userDir = Utility.userId().toString();

            // 解决中文问题，liunx下中文路径，图片显示问题
            fileName = UUID.randomUUID() + suffixName;
            String fileUrl = "/" + userDir + "/" + fileName;
            filePath = filePath + fileUrl;
            File dest = new File(filePath);

            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);
            class Result {
                String url;
                String ocr;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getOcr() {
                    return ocr;
                }

                public void setOcr(String ocr) {
                    this.ocr = ocr;
                }

                public Result(String url, String ocr) {

                    this.url = url;
                    this.ocr = ocr;
                }
            }
            Result result = new Result(fileUrl, ocrReco(fileUrl));
            baseResult = new Response(Response.Success, result, "success");
        } catch (Exception e) {
            baseResult = new Response(Response.Exception, e, "success");

        }
        return baseResult;
    }
}
