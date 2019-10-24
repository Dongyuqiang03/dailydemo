package com.example.dyq.javaguide.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/10/16 14:28
 * @Description:
 */
@Setter
@Getter
@ToString
public class Car {
    private String name;
    private String length;
    private String width;
    private String height;
    private Wheel wheel;
}
