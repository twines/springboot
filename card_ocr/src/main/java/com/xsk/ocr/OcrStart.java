package com.xsk.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OcrStart {
    public static void main(String[] args) {
        String sysPath = null;
        try {
            sysPath = ResourceUtils.getFile("classpath:").getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String imgPath = sysPath+"/Img.jpeg";
        String tranPath = sysPath;
        System.out.println(imgPath);
        File imageFile = new File(imgPath);
        ITesseract instance = new Tesseract();
        instance.setDatapath(tranPath);
        instance.setLanguage("chi_sim");
//        instance.setLanguage("eng");

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
