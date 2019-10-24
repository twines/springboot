package com.twins.lee.response;

import reactor.util.annotation.Nullable;

public class UploadResult {
    String url;
    @Nullable
    String ocr;
    String qr;

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

    public UploadResult(String url, String ocr, String qr) {

        this.url = url;
        this.ocr = ocr;
        this.qr = qr;
    }

    public UploadResult(String url) {
        this(url, null, null);
    }
}
