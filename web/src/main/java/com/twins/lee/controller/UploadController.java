package com.twins.lee.controller;

import com.twins.lee.model.BaseModel;
import com.twins.lee.service.IImprovService;
import com.twins.lee.utilites.Utility;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import reactor.util.annotation.Nullable;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class UploadController {

    @Resource
    IImprovService improvService;
    @Value("${twins.uploadFolder}")
    private String docLocation;

    @RequestMapping("/upload")
    @ResponseBody
    public BaseModel upload(@RequestParam("file") MultipartFile file,
                            @RequestParam(value = "needOCR", required = false, defaultValue = "false") boolean needOcr) {


        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        BaseModel baseResult = null;
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
            Result result = null;
            if (needOcr) {
                result = new Result(fileUrl, ocrReco(fileUrl));
            } else {
                result = new Result(fileUrl, null);
            }
            baseResult = new BaseModel(BaseModel.Success, result, "success");
        } catch (Exception e) {
            baseResult = new BaseModel(BaseModel.Exception, e, "success");
        }
        return baseResult;
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

    class Result {
        String url;
        @Nullable
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

        public Result(String url) {
            this(url, null);
        }
    }

}
