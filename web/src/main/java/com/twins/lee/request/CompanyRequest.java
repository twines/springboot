package com.twins.lee.request;

import java.util.Arrays;
import java.util.List;

public class CompanyRequest {
     public Long id;
     public Long userId;
     public String creditCode;
     public String userName;
     public String idNumber;
     public String bankNumber;
     public String mobile;
     public String cardA;
     public String cardB;
     public String businessLicense;
     public List<String> assetProof;
     public int status;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public Long getUserId() {
          return userId;
     }

     public void setUserId(Long userId) {
          this.userId = userId;
     }

     public String getCreditCode() {
          return creditCode;
     }

     public void setCreditCode(String creditCode) {
          this.creditCode = creditCode;
     }

     public String getUserName() {
          return userName;
     }

     public void setUserName(String userName) {
          this.userName = userName;
     }

     public String getIdNumber() {
          return idNumber;
     }

     public void setIdNumber(String idNumber) {
          this.idNumber = idNumber;
     }

     public String getBankNumber() {
          return bankNumber;
     }

     public void setBankNumber(String bankNumber) {
          this.bankNumber = bankNumber;
     }

     public String getMobile() {
          return mobile;
     }

     public void setMobile(String mobile) {
          this.mobile = mobile;
     }

     public String getCardA() {
          return cardA;
     }

     public void setCardA(String cardA) {
          this.cardA = cardA;
     }

     public String getCardB() {
          return cardB;
     }

     public void setCardB(String cardB) {
          this.cardB = cardB;
     }

     public String getBusinessLicense() {
          return businessLicense;
     }

     public void setAssetProof(List<String> assetProof) {
          this.assetProof = assetProof;
     }

     public String getAssetProof() {
          return String.join(",", assetProof);
     }

     public void setBusinessLicense(String businessLicense) {
          this.businessLicense = businessLicense;
     }

     public int getStatus() {
          return status;
     }

     public void setStatus(int status) {
          this.status = status;
     }
}
