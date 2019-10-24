package com.example.dyq;

import com.example.dyq.javaguide.spring.Car;
import com.example.dyq.javaguide.spring.SimpleIOC;
import com.example.dyq.javaguide.spring.Wheel;
import org.junit.Test;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/10/16 14:32
 * @Description:
 */
public class SimpleIocTest {
    @Test
    public void getBean() throws Exception {
        String location = SimpleIOC.class.getClassLoader().getResource("classpath:ioc.xml").getFile();//暂加载不到配置文件
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
