package com.twins.lee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("author")
public class Author {
    private int id;
    private int status;
    @TableField("social_code")
    private String SocialCode;
    @TableField("legal_person_name")
    private String legalPersonName;
    @TableField("id_card_name")
    private String idCardName;
    @TableField("id_card_serial")
    private String idCardSerial;
    @TableField("business_license")
    private String businessLicense;
    @TableField("id_card_up")
    private String idCardUp;
    @TableField("id_card_down")
    private String idCardDown;
    @TableField("user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSocialCode() {
        return SocialCode;
    }

    public void setSocialCode(String socialCode) {
        SocialCode = socialCode;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getIdCardSerial() {
        return idCardSerial;
    }

    public void setIdCardSerial(String idCardSerial) {
        this.idCardSerial = idCardSerial;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getIdCardUp() {
        return idCardUp;
    }

    public void setIdCardUp(String idCardUp) {
        this.idCardUp = idCardUp;
    }

    public String getIdCardDown() {
        return idCardDown;
    }

    public void setIdCardDown(String idCardDown) {
        this.idCardDown = idCardDown;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
