package com.example.dyq.util;

import java.util.*;

/**
 * @author: dongyuqiang
 * @date: 2020/8/13 13:36
 * @description:
 */
public class QrCodePayTest {


    public static void main(String[] args) throws Exception {
        //模拟终端发起交易
        //计算mac
        Map<String,String> map=new HashMap<>();
        //厂商型号
        map.put("appid","TM000003");
        map.put("version","v1.0.5");
        map.put("imei","864532041533149");
        map.put("simno","18B6F7024C68");
        map.put("mkey","qrpay");
        //sn号
        map.put("sn","86837030120003");
        //商户号
        map.put("mer","631000020000002");
        //交易金额 单位分
        map.put("f4","1000");
        //终端流水
        map.put("f11","00006");
        //基站信息
        map.put("loc","18BE,5028,460,00");
        map.put("mac","12321321321");
        //59域
//        map.put("f59","sfsfsfsf");
        Map<String, String> req = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });
        Set<String> keySetmap = map.keySet();
        Iterator<String> itermap = keySetmap.iterator();
        while (itermap.hasNext()) {
            String key = itermap.next();
                req.put(key, map.get(key));
        }

//        StringBuilder sb = new StringBuilder();
//        Set<String> keySet = map.keySet();
//        Iterator<String> iter = keySet.iterator();
//        while (iter.hasNext()) {
//            String pkey = iter.next();
//            if(!pkey.equals("mac")){
//                sb.append(pkey);
//                sb.append("=");
//                sb.append(map.get(pkey));
//                if(iter.hasNext()){
//                    sb.append("&");
//                }
//            }
//        }

//        String date =DESDOUtil.encECB3Des("appid=TM000001&version=v1.0.1&simno=18B6F7024C68&imei=864532041533149&mkey=qrPay&f4=1&f11=000001&sn=86837040000005&loc=18BE,5028,460&mer=631000015537670&f59=1232321&pvip=1&mac="+mac, key);
//     http://spos.qianziworth.cn:7781/semp/getH5Url.do  http://localhost:8081/getH5Url.do
        System.out.println(req);
        String response = Httpclient.sendRequestMethodWithHeader(req, "http://uspos.worthtech.net:8871/semp/getH5Url.do", "POST", 100);
        System.out.println(response);

//        String data = DESDOUtil.decECB3Des(response, key).trim();

//        System.out.println(data);
    }
}
