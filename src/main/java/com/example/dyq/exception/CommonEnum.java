package com.example.dyq.exception;

public enum CommonEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS("0000", "操作成功!"),
    CONFIG_NOT_FOUND("1001", "配置丢失"),
    SIGNATURE_NOT_MATCH("1002", "验签失败"),
    PARAMS_NOT_MATCH("1003", "参数缺失"),
    PARAMS_NOT_LEGAL("1004", "参数非法"),
    FUNC_NOT_SUP("1005", "请求的功能不支持"),
    INTERNAL_SERVER_ERROR("1006", "系统异常"),
    IPADDR_NOT_LEGAL("1009", "IP地址非法"),
    SERVER_BUSY("9999", "服务器正忙，请稍后再试!");

    /**
     * 错误码
     */
    private String resultCode;

    /**
     * 错误描述
     */
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
