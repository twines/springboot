package com.twins.lee.request;

import java.util.List;

public class AuthRequest {
    public int id;
    public String socialCode;
    public String legalPersonName;
    public String idCardName;
    public String idCardSerial;
    public String businessLicense;
    public String idCardUp;
    public String idCardDown;

//资产证明
    public List<AssetBill> assetBills;
    public TradeBill tradeBill;
}
