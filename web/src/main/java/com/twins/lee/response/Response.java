package com.twins.lee.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Authoe hanyun
 * @Email 1355081829@qq.com
 * @Date 2019/9/5
 **/
public class Response {
    public static Map<String, Object> success(Object data, String msg, int code) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> success(String msg, int code) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("data", new ArrayList<>());
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> success(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", new ArrayList<>());
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> success() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", new ArrayList<>());
        result.put("msg", "success");
        return result;
    }

    public static Map<String, Object> error(String msg, int code) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> error(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> error() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", "error");
        return result;
    }
}
