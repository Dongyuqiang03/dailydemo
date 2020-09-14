package com.example.dyq.util;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author: dongyuqiang
 * @date: 2020/6/24 14:50
 * @description:
 */
public class TestMain {
    public static void main(String[] args) {
//        DateTime end_date = DateTime.parse("2020-07-04", DateTimeFormat.forPattern("yyyy-MM-dd"));
//        DateTime noe = new DateTime(DateTime.now().toLocalDate().toString());
//        Period p2 = new Period(noe, end_date, PeriodType.days());
//        System.out.println("====:"+(end_date.plusDays(1).isAfter(noe.getMillis())));
//        System.out.println("时间差："+(p2.getDays()>10));
//        System.out.println("操作流水："+getOrderIdByUUId());
//        int i = ("v1.0.2").compareToIgnoreCase("v1.0.1");
//        System.out.println(i);
//        String s1="9A246311E3133DE117339A13DB6BC9DF|CD03F477FB352D80|1DB3C81B800F7BB6|1AC4282526874395|1|1|2030-01-01|1000.00|396.00|1|1|9.00|2030-01-01";
//        String[] split = s1.split("\\|");
//        System.out.println(split[0]);
//
//        boolean blank = StringUtils.isBlank(null);
//        System.out.println(blank);
//        Object s=StringUtils.isBlank(null)? 0:"1232";
//        System.out.println(s);
        String s="12.00";
        System.out.println(new BigDecimal(s).intValue());

    }

    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

}
