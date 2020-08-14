package com.example.dyq.entity;

import java.util.List;
import java.util.Map;

/**
 * @author: dongyuqiang
 * @date: 2020/7/17 15:27
 * @description:
 */
public class MerchantEnterpriseEntity extends AppBaseEntity {
    /**
     * 商户类型 0-企业 1-个体户 2-个人
     */
    private String merMode;

    /**
     * 注册名称
     */
    private String licName;

    /**
     * 工商注册号/统一社会信用码（营业执照）,merMode为0,1时，必传
     */
    private String merLic;

    /**
     * 营业执照有效期起始日期 yyyyMMdd ，merMode为0,1时，必传
     */
    private String licValidStart;

    /**
     * 营业执照有效期截止日期 yyyyMMdd，merMode为0时，必传
     */
    private String licValidEnd;

    /**
     * MCC大类
     */
    private String mccMainCateGory;

    /**
     * MCC小类
     */
    private String mccSubCateGory;

    private String licArea;

    /**
     * 注册地址
     */
    private String licAddr;

    /**
     * 商户法定代表人姓名
     */
    private String merLegal;
    /**
     * 法人手机号
     */
    private String legalPhone;

    /**
     * 法人证件类型：0-居民身份证或临时身份证;1-外国公民护照;2-港澳居民来往大陆通行证或其他有效旅游证件；3-其他类个人身份有效证件；4-单位证件；5-军人或武警身份证件
     */
    private String legalType;

    /**
     * 法人证件号码
     */
    private String legalCode;

    /**
     * 法人证件有效期起始日期 yyMMdd
     */
    private String legalValidStart;

    /**
     * 法人证件有效期截止日期 yyMMdd
     */
    private String legalValidEnd;

    private List<Map<String,Object>> picUrlList;

    public String getMerMode() {
        return merMode;
    }

    public void setMerMode(String merMode) {
        this.merMode = merMode;
    }

    public String getLicName() {
        return licName;
    }

    public void setLicName(String licName) {
        this.licName = licName;
    }

    public String getMerLic() {
        return merLic;
    }

    public void setMerLic(String merLic) {
        this.merLic = merLic;
    }

    public String getLicValidStart() {
        return licValidStart;
    }

    public void setLicValidStart(String licValidStart) {
        this.licValidStart = licValidStart;
    }

    public String getLicValidEnd() {
        return licValidEnd;
    }

    public void setLicValidEnd(String licValidEnd) {
        this.licValidEnd = licValidEnd;
    }

    public String getMccMainCateGory() {
        return mccMainCateGory;
    }

    public void setMccMainCateGory(String mccMainCateGory) {
        this.mccMainCateGory = mccMainCateGory;
    }

    public String getMccSubCateGory() {
        return mccSubCateGory;
    }

    public void setMccSubCateGory(String mccSubCateGory) {
        this.mccSubCateGory = mccSubCateGory;
    }

    public String getLicArea() {
        return licArea;
    }

    public void setLicArea(String licArea) {
        this.licArea = licArea;
    }

    public String getLicAddr() {
        return licAddr;
    }

    public void setLicAddr(String licAddr) {
        this.licAddr = licAddr;
    }

    public String getMerLegal() {
        return merLegal;
    }

    public void setMerLegal(String merLegal) {
        this.merLegal = merLegal;
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone;
    }

    public String getLegalType() {
        return legalType;
    }

    public void setLegalType(String legalType) {
        this.legalType = legalType;
    }

    public String getLegalCode() {
        return legalCode;
    }

    public void setLegalCode(String legalCode) {
        this.legalCode = legalCode;
    }

    public String getLegalValidStart() {
        return legalValidStart;
    }

    public void setLegalValidStart(String legalValidStart) {
        this.legalValidStart = legalValidStart;
    }

    public String getLegalValidEnd() {
        return legalValidEnd;
    }

    public void setLegalValidEnd(String legalValidEnd) {
        this.legalValidEnd = legalValidEnd;
    }

    public List<Map<String, Object>> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<Map<String, Object>> picUrlList) {
        this.picUrlList = picUrlList;
    }
}
