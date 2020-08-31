package com.example.dyq.util;

import org.apache.commons.lang3.StringUtils;

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

        boolean blank = StringUtils.isBlank(null);
        System.out.println(blank);
        Object s=StringUtils.isBlank(null)? 0:"1232";
        System.out.println(s);
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
