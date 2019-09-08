package com.twins.lee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Improv implements Serializable {
   public static class State {

        //待完善
        public static final int NeededInproved = 0;
        //已经完善
        public static final int Improved = 1;
        //        请求征信中
        public static final int CreditReporting = 2;
//        public  static  final  int
    }

    private Long id;
    private String code;
    @TableField("code_url")
    private String codeUrl;
    private String name;
    @TableField("name_url")
    private String nameUrl;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl;
    }
}
