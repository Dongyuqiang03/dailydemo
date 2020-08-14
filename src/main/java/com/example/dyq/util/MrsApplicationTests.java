package com.example.dyq.util;

import com.example.dyq.entity.PicData;
import com.example.dyq.entity.PicUploadEntity;
import com.example.dyq.entity.RequestIndustrialDatagram;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MrsApplicationTests {


    @Test
    public void testCreateSign(){

        RequestIndustrialDatagram datagram = new RequestIndustrialDatagram();
        datagram.setAgentNo("0000000001");
        PicUploadEntity picUploadEntity = new PicUploadEntity();
        picUploadEntity.setMerchantInnerNo("1111111111111");
        picUploadEntity.setMerchantOrganization("02");
        PicData picData = new PicData();
        picData.setPicMode("01");
        picData.setPicUrl("http://wangpuguanjia.oss-cn-shanghai.aliyuncs.com/app/ios/WPZS/5D1A5D8AE743467F8C6E223AFB3DC620.png");
        picUploadEntity.setPicData(picData);
        datagram.setData(picUploadEntity);
        datagram.setReqTrace("L2K3A4G5FN345Q34W");
        datagram.setVersion("1.0");

        System.out.println(datagram.encryptDESString("e10adc3949ba59abbe56e057f20f883e"));

    }

}
