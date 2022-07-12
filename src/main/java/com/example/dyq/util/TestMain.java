package com.example.dyq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dyq.entity.BrokerNumEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author: dongyuqiang
 * @date: 2020/6/24 14:50
 * @description:
 */
public class TestMain {
    public static void main(String[] args) throws IOException {
//        BooleanTest booleanTest = new BooleanTest();
//        System.out.println(booleanTest.getFlag());
        String s="http://liancheng-file.oss-cn-shanghai.aliyuncs.com/312022228997.jpg";
        String s1="http:null";
        String s2="http:http://liancheng-file.oss-cn-shanghai.aliyuncs.com/312022228997.jpg";
        String[] split = s2.split(":");
        boolean http = split[1].equals("http") ? true : false;
        System.out.println(http);

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




    public static String filterNullField(){
        String s="{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"操作成功！\",\n" +
                "  \"code\": 200,\n" +
                "  \"result\": {\n" +
                "    \"statusCode\": \"00\",\n" +
                "    \"statusMsg\": \"SUCCESS\",\n" +
                "    \"businessNo\": null,\n" +
                "    \"businessStatus\": \"审核通过\",\n" +
                "    \"housingCode\": \"2021102701906\",\n" +
                "    \"qrCodeUrl\": \"http://gs.nnfcxx.com:8010/gs/home/qrcode?type=69012&bizid=15528819\",\n" +
                "    \"mortgaged\": \"1\",\n" +
                "    \"limited\": \"0\",\n" +
                "    \"onRent\": \"0\",\n" +
                "    \"seize\": null,\n" +
                "    \"securityHousing\": null,\n" +
                "    \"decrepitHouse\": null,\n" +
                "    \"leaseRecord\": null,\n" +
                "    \"reason\": null,\n" +
                "    \"districtName\": \"良庆区\",\n" +
                "    \"location\": \"南宁市良庆区平乐大道37号南宁华润佳成五象中心二十四城16号楼二十五层2506号\",\n" +
                "    \"projectName\": null,\n" +
                "    \"street\": \"平乐大道\",\n" +
                "    \"houseNo\": null,\n" +
                "    \"buildingNo\": null,\n" +
                "    \"roomNo\": null,\n" +
                "    \"houseStructure\": null,\n" +
                "    \"sharedArea\": null,\n" +
                "    \"buildingArea\": 88.66,\n" +
                "    \"insideArea\": 0.0,\n" +
                "    \"housingUse\": null,\n" +
                "    \"startFloor\": null,\n" +
                "    \"terminationFloor\": null,\n" +
                "    \"countyCode\": null,\n" +
                "    \"realEstateUnitNo\": null,\n" +
                "    \"buildingTotalFloor\": null,\n" +
                "    \"upperFloorNum\": null,\n" +
                "    \"undergroundFloorNum\": null,\n" +
                "    \"elevator\": null,\n" +
                "    \"houseType\": null,\n" +
                "    \"householdStructure\": null,\n" +
                "    \"houseNature\": null,\n" +
                "    \"rightHolderName\": null\n" +
                "  },\n" +
                "  \"timestamp\": 1636456606846\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject result = jsonObject.getJSONObject("result");
        return result.toJSONString();
    }

    static class BooleanTest{
        boolean flag;

        public boolean getFlag() {
            return flag;
        }

        public BooleanTest setFlag(boolean flag) {
            this.flag = flag;
            return this;
        }
    }
}
