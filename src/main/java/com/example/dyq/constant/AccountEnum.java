package com.example.dyq.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: dyq
 * @Date: 2020/5/28 13:46
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum AccountEnum {
    //账户类型
    ACCOUNT_TYPE_ENUM_1("结算账户","1"),
    ACCOUNT_TYPE_ENUM_2("信用账户","2"),
    ACCOUNT_TYPE_ENUM_3("自由资金账户","3"),
    ACCOUNT_TYPE_ENUM_4("活动账户","4"),

    //用户类型
    USER_CATEGORY_ENUM_0("实壹","0"),
    USER_CATEGORY_ENUM_1("商户","1"),
    USER_CATEGORY_ENUM_2("代理商","2"),
    USER_CATEGORY_ENUM_3("运营中心/分公司","3"),

    //账户状态
    ACCOUNT_STATUS_1("正常","1"),
    ACCOUNT_STATUS_2("冻结","2"),

    //代理等级
    AGENT_CATEGORY_100279999("运营中心","100279999"),
    AGENT_CATEGORY_100280000("一级","100280000"),
    AGENT_CATEGORY_100280001("二级","100280001"),
    AGENT_CATEGORY_100280002("三级","100280002"),
    AGENT_CATEGORY_100280003("四级","100280003"),
    AGENT_CATEGORY_100280004("五级","100280004"),
    AGENT_CATEGORY_100280005("六级","100280005"),
    AGENT_CATEGORY_100280006("七级","100280006"),
    AGENT_CATEGORY_100280007("八级","100280007"),

    //代理商资金处理方式
    AGENT_AMT_DEAL_TYP_0("正常记账分润","0"),
    AGENT_AMT_DEAL_TYP_1("代理商提现费进入提现冻结账户","1"),

    //用户沉默状态
    SILENT_CUSTOMER_STAUS_0("非沉默用户","0"),
    SILENT_CUSTOMER_STAUS_1("沉默用户实名认证","1"),
    SILENT_CUSTOMER_STAUS_2("沉默用户非实名认证","2"),
    //各app平台appId
    APP_PLATFORM_SPOS_ID("小喔电签","003"),
    APP_PLATFORM_MPOS_ID("大蓝牙","002"),
    APP_PLATFORM_SMALL_BLUETOOTH_ID("小蓝牙","001"),

    //机构
    ORG_FLAG_2("浙江喔刷","2"),

    //刷卡方式
    SWIPING_CARD_WAY_01("刷卡","01"),
    SWIPING_CARD_WAY_02("插卡","02"),
    SWIPING_CARD_WAY_03("非接(实体卡)","03"),
    SWIPING_CARD_WAY_04("手机等虚拟卡","04");

    private String codeDesc;
    private String code;
}
