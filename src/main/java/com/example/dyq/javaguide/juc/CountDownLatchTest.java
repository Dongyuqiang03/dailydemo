package com.example.dyq.javaguide.juc;

/**
 * @author: dongyuqiang
 * @date: 2020/8/13 17:53
 * @description:
 */

import com.alibaba.fastjson.JSONArray;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountDownLatchTest {
    private static final int RUNNER_COUNT = 10;
    public static void main(String[] args) throws InterruptedException {
        String picUrlList="[{\"picMode\":\"05\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/080ba6ad-fb55-482f-b5a5-6513c660b441.png\"},{\"picMode\":\"01\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/6212e46e-3b56-4b12-898e-842e4eaba0eb.png\"},{\"picMode\":\"02\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/2042fefd-d7b0-4070-933a-757a3f9c79d6.png\"},{\"picMode\":\"08\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/bca60dbe-e867-4663-90ae-17083a5ee78b.png\"},{\"picMode\":\"06\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/66a0f36d-face-40b1-b397-e884f619dd42.png\"},{\"picMode\":\"07\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/09f0f4af-90f9-40e0-9bc3-d827b4797ccd.png\"},{\"picMode\":\"18\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/711d8dc1-3ac2-402d-acf5-6328eb5c603b.png\"},{\"picMode\":\"03\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/6212e46e-3b56-4b12-898e-842e4eaba0eb.png\"},{\"picMode\":\"04\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/2042fefd-d7b0-4070-933a-757a3f9c79d6.png\"},{\"picMode\":\"09\",\"picUrl\":\"https://woshuaapp.oss-cn-hangzhou.aliyuncs.com//telegraph/android//networdAccess/20200813/bca60dbe-e867-4663-90ae-17083a5ee78b.png\"}]";

        JSONArray jsonArray = JSONArray.parseArray(picUrlList);
        int size = jsonArray.size();
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(size);
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < size; i++) {
            final int NO = i + 1;
            Runnable run = () -> {
                try {
                    begin.await();
                    Thread.sleep((long)(Math.random() * 10000));
                    System.out.println("No." + NO + " 业务处理");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            };
            Future<?> submit = exec.submit(run);
            submit.isDone();
        }

        System.out.println("Game Start ...");
        begin.countDown();
        end.await();
        System.out.println("Game Over.");

        exec.shutdown();
    }


    /**
     * 易生图片上传接口参数封装
     *
     * @return
     */
//    public String uploadPicParamPack(Map<String,Object> merchantPicInfo, String randomString) {
//        RequestIndustrialDatagram datagram = new RequestIndustrialDatagram();
//        datagram.setAgentNo("0000000001");
//        PicUploadEntity picUploadEntity = new PicUploadEntity();
//        picUploadEntity.setMerchantInnerNo("1111111111111");
//        picUploadEntity.setMerchantOrganization("02");
//        PicData picData = new PicData();
//        picData.setPicMode(merchantPicInfo.get("picMode"));
//        picData.setPicUrl(merchantPicInfo.get("picUrl"));
//        picUploadEntity.setPicData(picData);
//        datagram.setData(picUploadEntity);
//        datagram.setReqTrace(randomString);
//        datagram.setVersion("1.0");
//        datagram.encryptDESString(encrypt_des_key);
//        String requestParam = JSONObject.toJSONString(datagram);
//        logger.info("图片上传接口请求参数======>上传地址：" + picUploadUrl + " picMode:" + picData.getPicMode() + " 图片地址：" + picData.getPicUrl());
//        return requestParam;
//    }
}
