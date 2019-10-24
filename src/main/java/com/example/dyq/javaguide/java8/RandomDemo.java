package com.example.dyq.javaguide.java8;

import java.util.Random;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/10/14 15:05
 * @Description:
 */
public class RandomDemo {


    public static void main(String[] args) {
        Random random = new Random();
        int asInt = random.ints(100, 200).limit(10).findFirst().getAsInt();
        System.out.println(asInt);
    }

}
