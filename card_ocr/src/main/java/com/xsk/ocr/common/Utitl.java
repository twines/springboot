package com.xsk.ocr.common;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class Utitl {
    public static void ocr(String imgName, OcrResult ocrResult) {
        String sysPath = null;
        try {
            sysPath = ResourceUtils.getFile("classpath:").getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (ocrResult != null) {
                ocrResult.onFailed(e);
                return;
            }
        }
        String imgPath = sysPath + "/" + imgName;
        String tranPath = sysPath;
        System.out.println(imgPath);
        File imageFile = new File(imgPath);

        ocr(imageFile,ocrResult);
    }

    public static String ocr(File imageFile, OcrResult ocrResult) {
        String sysPath = null;
        try {
            sysPath = ResourceUtils.getFile("classpath:").getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String tranPath = sysPath;

        ITesseract instance = new Tesseract();
        instance.setDatapath(tranPath);
        instance.setLanguage("chi_sim");
//        instance.setLanguage("eng");
        String commonCode = "";
        String ownerCode = "";
        String result = null;
        try {
              result = instance.doOCR(imageFile).replace(" ", "");
            if (ocrResult != null) {
                ocrResult.onSucces(result);
            }

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            if (ocrResult != null) {
                ocrResult.onFailed(e);
            }
        }
        return result;
    }


    public static String card(File file) {
        return ocr(file, null);
    }
    public interface OcrResult {
        void onSucces(String result);

        void onFailed(Exception e);
    }
}
