package com.xsk.ocr.model;

public class OcrResult<T> {
    T data;
    int code;
    String msg;

    public OcrResult(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public OcrResult(int code, String msg) {

        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public static final int OCR_FileMiss = 1;
    public static final int OCR_Exception = 2;
    public static final int OCR_Notfound = 3;
    public static final int OCR_Succcess = 0;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
