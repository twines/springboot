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

        final String[] commonCode = {""};
        final String[] ownerCode = {""};
        final String[] idCard = {null};


                ocr("card.jpeg", new OcrResult() {
                    @Override
                    public void onSucces(String result) {
                        Pattern pattern = Pattern.compile("社会信用代码+\\d+p");
                        Matcher matcher = pattern.matcher(result);
                        if (matcher.find()) {
                            commonCode[0] = matcher.group();
                        }
                        matcher = Pattern.compile("D法定代表人[^\\x00-\\xff]{2,4},C").matcher(result);
                        if (matcher.find()) {
                            ownerCode[0] = matcher.group();
                        }
                        System.out.println(commonCode);
                        System.out.println(ownerCode);
                    }

                    @Override
                    public void onFailed(Exception e) {

                    }
                });



                ocr("idcard.jpg", new OcrResult() {
                    @Override
                    public void onSucces(String result) {
                        Pattern pattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)");
                        Matcher matcher = pattern.matcher(result);
                        if (matcher.find()) {
                            idCard[0] = matcher.group();
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {

                    }
                });

        System.out.println(commonCode[0]+"\t"+ownerCode[0]+"\t"+idCard[0]);
    }

    private static void ocr(String imgName, OcrResult ocrResult) {
        String sysPath = null;
        try {
            sysPath = ResourceUtils.getFile("classpath:").getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String imgPath = sysPath + "/" + imgName;
        String tranPath = sysPath;
        System.out.println(imgPath);
        File imageFile = new File(imgPath);
        ITesseract instance = new Tesseract();
        instance.setDatapath(tranPath);
        instance.setLanguage("chi_sim");
//        instance.setLanguage("eng");
        String commonCode = "";
        String ownerCode = "";
        try {
            String result = instance.doOCR(imageFile).replace(" ", "");
            if (ocrResult != null) {
                ocrResult.onSucces(result);
            }

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            if (ocrResult != null) {
                ocrResult.onFailed(e);
            }
        }
    }

    protected interface OcrResult {
        void onSucces(String result);

        void onFailed(Exception e);
    }
}
