package com.example.dyq.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: dyq
 * @Date: 2020/5/28 11:48
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum TranCodeEmum {

    TRAN_CODE_EMUM_100(100, "T0消费"),
    TRAN_CODE_EMUM_101(101, "T1消费"),
    TRAN_CODE_EMUM_102(102, "代付"),
    TRAN_CODE_EMUM_108(108, "T0_二维码"),
    TRAN_CODE_EMUM_117(117, "T1_二维码");

    private  int tranCode;
    private  String tranCodeDesc;
}
