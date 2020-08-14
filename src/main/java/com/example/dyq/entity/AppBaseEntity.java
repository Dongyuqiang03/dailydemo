package com.example.dyq.entity;

/**
 * @author: dongyuqiang
 * @date: 2020/7/17 15:17
 * @description: http请求公共参数实体
 */
public class AppBaseEntity {

    private String app_id;
    private String i_version;
    private String platform_id;
    private String platform_version;
    private String app_down_platform;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getI_version() {
        return i_version;
    }

    public void setI_version(String i_version) {
        this.i_version = i_version;
    }

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_version() {
        return platform_version;
    }

    public void setPlatform_version(String platform_version) {
        this.platform_version = platform_version;
    }

    public String getApp_down_platform() {
        return app_down_platform;
    }

    public void setApp_down_platform(String app_down_platform) {
        this.app_down_platform = app_down_platform;
    }
}
