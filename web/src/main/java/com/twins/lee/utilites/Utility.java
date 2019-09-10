package com.twins.lee.utilites;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
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

    public static Long userId() {
        String strId = (String) userInfo().get("id");
        return Long.parseLong(strId);
    }
    public static Map userInfo() {
        Subject subject = SecurityUtils.getSubject();
        // 第一个放的是id
        //第二放的是用户json
        List infos = subject.getPrincipals().asList();
        if (infos.size() == 1) {
            return null;
        }

        return (Map) infos.get(1);
    }
}
