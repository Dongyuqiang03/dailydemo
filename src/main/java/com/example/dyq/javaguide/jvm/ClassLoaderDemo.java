package com.example.dyq.javaguide.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/9/9 15:22
 * @Description:
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
        Map<String,Object> map=new HashMap<>();
        map.put("1","1");
        map.remove("2");



    }
}
