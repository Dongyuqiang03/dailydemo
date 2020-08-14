package com.example.dyq.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dyq.entity.MerchantEnterpriseEntity;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dongyuqiang
 * @date: 2020/7/22 14:04
 * @description:
 */
public class MapUtil {

    /**
     * 将map转换为一个对象
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    public static void main(String[] args) throws Exception {

        Map map=new HashMap<>();
        map.put("accType","10");
        map.put("app_down_platform","appstore");
        map.put("app_id","003");
        map.put("platform_versioin","123");
        map.put("i_version","1.0.");
        map.put("businName","就很尴尬");
        map.put("businScope","123");
        map.put("businScopeM","123");
        map.put("isLegalName","123");
        map.put("linkMan","123");
        map.put("linkManPhone","123");
        map.put("mccMainCateGory","123");
        map.put("mccSubCateGory","123");
        map.put("merMode","123");
        map.put("merType","123");
        map.put("merTypeM","123");
        Map map1=new HashMap();
        map1.put("picMode","1");
        map1.put("picUrl","hhhhhh");
        Map map2=new HashMap();
        map2.put("picMode","");
        map2.put("picUrl","1");

        List<Map> list=new ArrayList<>();
        list.add(map1);
        list.add(map2);
        map.put("picUrlList",list);
        map.put("1111","1111");
        System.out.println(map.toString());
        MerchantEnterpriseEntity s = (MerchantEnterpriseEntity) mapToObject(map, MerchantEnterpriseEntity.class);
        System.out.println(s.getApp_id()+s.getMerMode());
        String  JSON_ARRAY_STR = "[{\"picMode\":\"lily\",\"picUrl\":12},{\"picMode\":\"lucy\",\"picUrl\":15}]";
        map.put("picUrlList",JSON_ARRAY_STR);
        String picUrlList1 = (String) map.get("picUrlList");
        JSONArray jsonArray=JSONArray.parseArray(picUrlList1);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String picMode = jsonObject.getString("picMode");
            String picUrl = jsonObject.getString("picUrl");
            System.out.println(picMode+picUrl);
        }
    }
}


class User{
    private String name;
    private String id;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}