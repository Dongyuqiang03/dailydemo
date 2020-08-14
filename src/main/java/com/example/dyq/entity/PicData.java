package com.example.dyq.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PicData {

    @NotBlank(message = "图片类型不能为空")
    private String picMode; // 图片类型

    @NotBlank(message = "图片url不能为空")
    private String picUrl; // 图片url地址
}
