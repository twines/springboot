package com.xsk.ocr.controller;

import com.xsk.ocr.common.Utitl;
import com.xsk.ocr.model.OcrResult;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @RequestMapping("/idcard")
    @ResponseBody
    public OcrResult<Object> card(@RequestParam("file") MultipartFile file, @RequestParam("reg") String reg) throws FileNotFoundException {
        if (file.isEmpty()) {
            return new OcrResult(OcrResult.OCR_FileMiss, "缺少文件");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 文件上传路径
        String filePath = ResourceUtils.getFile("classpath:").getAbsolutePath();

        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        filePath = filePath + "/tmp/" + fileName;
        File dest = new File(filePath);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {

            file.transferTo(dest);
            String result = Utitl.card(dest);
            dest.deleteOnExit();
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(result);
            String regResult = null;
            if (matcher.find()) {
                regResult = matcher.group();
            }
            if (regResult == null) {
                return new OcrResult(result, OcrResult.OCR_Notfound, "无匹配数据");

            } else {
                return new OcrResult(regResult, OcrResult.OCR_Succcess, "成功");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new OcrResult(e, OcrResult.OCR_Exception, "系统异常");

        } catch (IOException e) {
            return new OcrResult(e, OcrResult.OCR_Exception, "系统异常");
        } catch (PatternSyntaxException e) {
            return new OcrResult(e, OcrResult.OCR_Exception, "正则参数异常");
        }
    }
}
