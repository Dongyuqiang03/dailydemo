package com.example.dyq.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @authr dyq
 * @Date: 2020/5/28 15:22
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum AccountTransLogEnum {
    //资金变动类型
    ACCOUNT_TRANS_LOG_ENUM_1("消费",1),
    ACCOUNT_TRANS_LOG_ENUM_2("消费",2),
    ACCOUNT_TRANS_LOG_ENUM_3("消费",3),
    ACCOUNT_TRANS_LOG_ENUM_4("消费",4),
    ACCOUNT_TRANS_LOG_ENUM_5("消费",5),
    ACCOUNT_TRANS_LOG_ENUM_6("消费",6),
    ACCOUNT_TRANS_LOG_ENUM_7("消费",7),
    ACCOUNT_TRANS_LOG_ENUM_8("消费",8),
    ACCOUNT_TRANS_LOG_ENUM_9("消费",9),
    ACCOUNT_TRANS_LOG_ENUM_10("消费",10),
    ACCOUNT_TRANS_LOG_ENUM_11("消费",11);

    private String codeDesc;
    private int code;
}
