package com.twins.lee.utilites;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

public class Utility {

    public static Map decodeLeDanInfo(Map<String,Object> userInfo) throws UnsupportedEncodingException {
        for (String key : userInfo.keySet()) {
            String userInfoValue = (String) userInfo.get(key);
            userInfoValue = URLDecoder.decode(userInfoValue, "utf-8");
            userInfo.put(key, userInfoValue);
        }
        return userInfo;
    }
}
