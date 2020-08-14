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
    //交易码
    TRAN_CODE_100(100, "T0消费"),
    TRAN_CODE_101(101, "T1消费"),
    TRAN_CODE_102(102, "代付"),
    TRAN_CODE_105(105, "手工代付"),
    TRAN_CODE_108(108, "T0_二维码"),
    TRAN_CODE_109(109, "消费冲正"),
    TRAN_CODE_112(112, "结算代付"),
    TRAN_CODE_113(113, "华势信用卡还款"),
    TRAN_CODE_118(118, "账户提现"),
    TRAN_CODE_117(117, "T1_二维码");

    private  int tranCode;
    private  String tranCodeDesc;
}
