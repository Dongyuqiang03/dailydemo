package com.example.dyq.entity;

import lombok.Data;

/**
 * @author dongyuqiang
 * @date 2022/03/03 11:45
 * @description 泰州政府接口请求基类
 **/
@Data
public class TaiZhouBaseReqDto {
    /**
     * 调用流水号
     */
    private Long sn;
    /**
     * 账户编号
     */
    private String accountcode;
    /**
     * 接口访问口令
     */
    private String accounttoken;
    /**
     * 企业统一社会信用代码
     */
    private String agentcardcode;
    /**
     * 经纪人员信息卡编号
     */
    private String usccode;
    /**
     * 房源码
     */
    private String housecode;

}