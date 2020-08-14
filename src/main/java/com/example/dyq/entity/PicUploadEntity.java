package com.example.dyq.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@ApiModel
@Data
public class PicUploadEntity extends RequestIndustrialDatagram {

    @NotBlank(message = "商户来源不能为空")
//    @ApiModelProperty(value = "商户号")
    /**
     * 商户号
     */
    private String merchantInnerNo;

    //    @NotBlank(message = "内部商户号不能为空")
//    @ApiModelProperty(value = "图片类型")
    /**
     * 图片类型
     */
    private String merchantOrganization;

    @NotNull(message = "图片信息不能为空")
    /**
     * 图片上传服务器后的url
     */
    private PicData picData;


}
