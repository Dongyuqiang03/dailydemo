package com.example.dyq.util;

import com.alibaba.fastjson.JSON;
import com.example.dyq.entity.WuhanHouseResultDto;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.CollectionUtils;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: dongyuqiang
 * @date: 2020/6/24 14:50
 * @description:
 */
public class TestMain {
    public static void main(String[] args) throws IOException {
//        DateTime end_date = DateTime.parse("2020-07-04", DateTimeFormat.forPattern("yyyy-MM-dd"));
//        DateTime noe = new DateTime(DateTime.now().toLocalDate().toString());
//        Period p2 = new Period(noe, end_date, PeriodType.days());
//        System.out.println("====:"+(end_date.plusDays(1).isAfter(noe.getMillis())));
//        System.out.println("时间差："+(p2.getDays()>10));
//        System.out.println("操作流水："+getOrderIdByUUId());
//        int i = ("v1.0.2").compareToIgnoreCase("v1.0.1");
//        System.out.println(i);
//        String s1="9A246311E3133DE117339A13DB6BC9DF|CD03F477FB352D80|1DB3C81B800F7BB6|1AC4282526874395|1|1|2030-01-01|1000.00|396.00|1|1|9.00|2030-01-01";
//        String[] split = s1.split("\\|");
//        System.out.println(split[0]);
//
//        boolean blank = StringUtils.isBlank(null);
//        System.out.println(blank);
//        Object s=StringUtils.isBlank(null)? 0:"1232";
//        System.out.println(s);
//        String s="12.00";
//        System.out.println(new BigDecimal(s).intValue());
//        List<String> ints=new ArrayList<>();
//        String platformType="android";
//        Integer wubaCityId=135;
//        if("android".equals(platformType)) {
//            //灰度城市，目前限制宁波、昆山、杭州，东莞、天津、大连 ,1是 0不是
//            String newPanoGray58cityshowAndroid = "135,16,79,413,18,14";
//            String[] split = newPanoGray58cityshowAndroid.split(",");
//            ints = Arrays.asList(split);
//            boolean flag = ints.contains(String.valueOf(wubaCityId));
//            if (flag) {
//                System.out.println("newPanoGrayFlag="+ 1);
//            } else {
//                System.out.println("newPanoGrayFlag="+ 0);
//            }
//        }else if("ios".equals(platformType)){
//            String newPanoGray58cityshowIos = "";
//            String[] split = newPanoGray58cityshowIos.split(",");
//            ints = Arrays.asList(split);
//            boolean flag = ints.contains(String.valueOf(wubaCityId));
//            if (flag) {
//                System.out.println("newPanoGrayFlag="+ 1);
//            } else {
//                System.out.println("newPanoGrayFlag="+ 0);
//            }
//        }else{
//            System.out.println("老版本");
//        }
//        String s="昆明新亚企航房地产经纪有限公司";
//        String s1="昆明新亚企航房地产经纪有限公司曙光分公司";
//        String comAllName = StringUtils.isBlank(s) ? s : s.trim();
//        String trim = s1.trim();
//        System.out.println(!trim.contains(comAllName));
//        System.out.println(!comAllName.contains(s1));
//        if (!trim.contains(comAllName) && !comAllName.contains(s1)) {
//            System.out.println("不一致");
//        }

//        String s="{\"company_violations\":[],\"store_violations\":[],\"person_violations\":[\"HFZJ21000027\",\"HFZJ21000027\",\"HFZJ21000027\",\"HFZJ21000027\"]}";
//        JsonNode jsonNode = JacksonJsonUtil.readTree(s);
//
//
//        JsonNode company_violations = jsonNode.get("company_violations");
//        String[] arr1 = JacksonJsonUtil.defaultReadValue(company_violations.toString(), new TypeReference<String[]>() {
//        });
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(arr1.length);
//
//        JsonNode store_violations = jsonNode.get("store_violations");
//        String[] arr3 = JacksonJsonUtil.defaultReadValue(store_violations.toString(), new TypeReference<String[]>() {
//        });
//        System.out.println(Arrays.toString(arr3));
//        System.out.println(arr3.length);
//
//        JsonNode person_violations = jsonNode.get("person_violations");
//        String[] arr2 = JacksonJsonUtil.defaultReadValue(person_violations.toString(), new TypeReference<String[]>() {
//        });
//        System.out.println(Arrays.toString(arr2));
//        System.out.println(arr2.length);

//        String s="authorization-token已过期!";
//        if(s.contains("已过期")){
//            System.out.println("已过期");
//        }
//        String response = "{\"code\":200,\"message\":\"success\",\"data\":{\"is_can_send\":1,\"cause\":\"\",\"license_number\":\"ZS21011154\",\"license_number_url\":\"http:\\/\\/www.hfrea.org.cn\\/oaPerson\\/detail\\/60b9cdf94902dc475c8c8eb4.html\",\"institution_record_no\":\"913401007548632595\",\"institution_record_no_url\":\"http:\\/\\/www.hfrea.org.cn\\/oaCompany\\/detail\\/5cb7e580a6c98abc468b458b.html\",\"sincerity_status\":\"诚信\"}}";
//        JsonNode jsonNode = JacksonJsonUtil.readTree(response);
//        int code = jsonNode.get("code").asInt();
//        String message = jsonNode.get("message").asText();
//        JsonNode data = jsonNode.get("data");
//        System.out.println(JacksonJsonUtil.defaultWriteValueAsStringIgnoreException(data));

//        String msg="";
//        JsonNode jsonNode = JacksonJsonUtil.readTree(msg);
//        boolean data1 = jsonNode.has("data");
//        if(data1) {
//            String data = jsonNode.get("data").asText();
//            System.out.println(data);
//        }
//        String msg = "获取token异常";
//        if(StringUtils.contains(msg,"token已过期")||StringUtils.contains(msg,"token异常")){
//            System.out.println("合肥政府接口token过期，不予处理");
//            return ;
//        }
        String ext=null;
        String code = JSON.parseObject(ext).getString("publisherType");
    }

    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    public static boolean isContains(int cityId) {
        Integer[] cityIds = {135, 16, 79, 413, 18, 147};
        List<Integer> ints = Arrays.asList(cityIds);
        boolean flag = ints.contains(cityId);

        return flag;
    }


    public static String qrFormat(String msg,Long propId){
        WuhanHouseResultDto resultDto = JSON.parseObject(msg, WuhanHouseResultDto.class);

        resultDto.setQr_url("http://wos/");
        return JacksonJsonUtil.defaultWriteValueAsStringIgnoreException(resultDto);
    }



}
