package com.example.dyq.javaguide.jvm;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/9/9 11:21
 * @Description:
 */
public class GCDemo {

    public static void main(String[] args) {
        byte[] allocation1, allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[32000*1024];
        allocation2 = new byte[1000*1024];
        allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
    }
}
