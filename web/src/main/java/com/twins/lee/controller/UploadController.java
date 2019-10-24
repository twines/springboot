package com.twins.lee.controller;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.HybridBinarizer;
import com.twins.lee.response.Response;
import com.twins.lee.response.UploadResult;
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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import static org.apache.catalina.manager.Constants.CHARSET;

@Controller
@RequestMapping("/file")
public class UploadController {

    @Resource
    IImprovService improvService;
    @Value("${twins.uploadFolder}")
    private String docLocation;

    @RequestMapping("/upload")
    @ResponseBody
    public Response upload(@RequestParam("file") MultipartFile file,
                           @RequestParam(value = "needOCR", required = false, defaultValue = "false") boolean needOcr,
                           @RequestParam(value = "needQr", required = false, defaultValue = "false") boolean needQr) {


        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        Response baseResponse = null;
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
            UploadResult result = null;
            if (needOcr && needQr == false) {
                result = new UploadResult(fileUrl, ocrReco(fileUrl), null);
            } else if (needQr && needOcr == false) {
                result = new UploadResult(fileUrl, null, qrReco(fileUrl));
            } else if (needQr && needOcr) {
                String ocr = ocrReco(fileUrl);
                String qr = qrReco(fileUrl);
                result = new UploadResult(fileUrl, ocr, qr);
            } else {
                result = new UploadResult(fileUrl, null, null);
            }
            baseResponse = new Response(Response.Success, result, "success");
        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                baseResponse = new Response(Response.Exception, e, "无法识别单据上的二维码信息");
            } else {
                baseResponse = new Response(Response.Exception, e, "success");
            }
        }
        return baseResponse;
    }

    protected String qrReco(String imgPath) throws IOException, NotFoundException {
        String destPath = docLocation + imgPath;

        BufferedImage image;
        image = ImageIO.read(new File(destPath));
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        com.google.zxing.Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);

        String resultStr = result.getText();

        return resultStr;
    }

    private String ocrReco(String imgPath) {
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


}
