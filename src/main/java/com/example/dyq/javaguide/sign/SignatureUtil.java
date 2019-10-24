package com.example.dyq.javaguide.sign;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/10/10 15:22
 * @Description: 签名工具类
 */
public class SignatureUtil {

    public static String getAlipaySignContent(Map<String, String> sortedParams){
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String)keys.get(i);
            String value = (String)sortedParams.get(key);
            if (areNotEmpty(new String[] { key, value }))
            {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }


    public static boolean areNotEmpty(String... values)
    {
        boolean result = true;
        if ((values == null) || (values.length == 0)) {
            result = false;
        } else {
            for (String value : values) {
                result &= !StringUtils.isEmpty(value);
            }
        }
        return result;
    }


}
