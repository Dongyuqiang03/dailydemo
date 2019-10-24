package com.example.dyq.javaguide.java8;

import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/9/10 15:06
 * @Description:
 */
public class OptionalsDemo {

    public static void main(String[] args) {
        String s=null;
        System.out.println(Optional.ofNullable(s).orElse("bbbb"));
    }

    public static void testOne() {
        // 参数不能是null
//        Optional<Integer> optional1 = Optional.of(1);

        // 参数可以是null
//        Optional<Integer> optional2 = Optional.ofNullable(null);

        // 参数可以是非null
//        Optional<Integer> optional3 = Optional.ofNullable(2);
        Optional<Integer> optional1 = Optional.ofNullable(null);
        Optional<Integer> optional2 = Optional.ofNullable(null);
        System.out.println(optional1 == optional2);// true
        System.out.println(optional1 == Optional.<Integer>empty());// true

        Object o1 = Optional.<Integer>empty();
        Object o2 = Optional.<String>empty();
        System.out.println(o1 == o2);// true
    }

    public static void testTwo(){
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // isPresent判断值是否存在
        System.out.println(optional1.isPresent() == true);
        System.out.println(optional2.isPresent() == false);
    }
}
