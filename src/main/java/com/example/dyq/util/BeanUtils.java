package com.example.dyq.util;

import com.example.dyq.exception.CustomerException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

//import com.shiyi.mrs.exception.CustomerException;

public class BeanUtils {

    /**
     * map转换成javaBean
     *
     * @param map
     * @return
     */
    public static Object transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            //1.获取bean信息
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : properties) {
                String key = prop.getName();
                if (map.containsKey(key) && map.get(key) != null) {
                    Object value = map.get(key);
                    Method setMethod = prop.getWriteMethod();
                    Class<?> propertyType = prop.getPropertyType();
                    setMethod.invoke(obj, propertyType.cast(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String bean2JSON(Object obj) {
        String errorMessage = "The operation of bean2Json has been error!";
        ObjectMapper objectMapper = null;
        StringBuffer strBuffer = new StringBuffer("");
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            strBuffer.append(objectMapper.writeValueAsString(obj));
        } catch (JsonGenerationException jge) {
            jge.printStackTrace();
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBuffer.toString();
    }

    public static <T> T json2Bean(String json, Class<T> clz) throws Exception {
        String errorMessage = "The operation of json2Bean has been error!";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            return objectMapper.readValue(json, clz);
        } catch (JsonParseException var4) {
            throw new CustomerException(errorMessage, var4);
        } catch (JsonMappingException var5) {
            throw new CustomerException(errorMessage, var5);
        } catch (IOException var6) {
            throw new CustomerException(errorMessage, var6);
        }
    }

}
