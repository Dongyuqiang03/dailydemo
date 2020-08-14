package com.example.dyq.util;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;

import java.util.UUID;

/**
 * @author: dongyuqiang
 * @date: 2020/6/24 14:50
 * @description:
 */
public class TestMain {
    public static void main(String[] args) {
        DateTime end_date = DateTime.parse("2020-07-04", DateTimeFormat.forPattern("yyyy-MM-dd"));
        DateTime noe = new DateTime(DateTime.now().toLocalDate().toString());
        Period p2 = new Period(noe, end_date, PeriodType.days());
        System.out.println("====:"+(end_date.plusDays(1).isAfter(noe.getMillis())));
        System.out.println("时间差："+(p2.getDays()>10));
        System.out.println("操作流水："+getOrderIdByUUId());
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
