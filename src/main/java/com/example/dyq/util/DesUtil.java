package com.example.dyq.util;

import com.example.dyq.exception.CustomerException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.math.BigDecimal;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;


public class DesUtil {

    /**
     * 3DES加密
     *
     * @param data 数据
     * @param key  密钥
     * @return
     */
    public static String des3Encode(String data, String key) throws CustomerException {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException(e.getMessage(), e);
        }
    }

    /**
     * 3DES解密
     *
     * @param key  密钥
     * @param data 密文
     * @return
     */
    public static String des3Dncode(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
            SecretKey sk = SecretKeyFactory.getInstance("DESede").generateSecret(dks);
            cipher.init(Cipher.DECRYPT_MODE, sk);
            byte[] result = cipher.doFinal(Base64.decodeBase64(data));
            return new String(result, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException(e.getMessage(), e);
        }
    }

    public static byte[] str2Bcd(String asc) {

        asc = asc.trim();
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte[] ret = new byte[len / 2];
        for (int i = 0; i < ret.length; i++) {
            try {
                ret[i] = (Integer.valueOf(asc.substring(i + i, (i + i + 2)), 16)).byteValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * des 加密
     *
     * @param data
     * @param key
     * @return
     */
    public static String desEncode(String data, String key) {
        try {
            byte[] dataBytes = data.getBytes("utf-8");
            int num = 8 - dataBytes.length % 8;
            byte[] encData = Arrays.copyOf(dataBytes, dataBytes.length + num);
            for (int i = dataBytes.length; i < encData.length; i++) {
                encData[i] = Integer.valueOf(0).byteValue();
            }
            byte[] bytes = str2Bcd(key);
            DESKeySpec desKey = new DESKeySpec(bytes);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return Base64.encodeBase64String(cipher.doFinal(encData));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * des 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String desDecode(String data, String key) throws Exception {
        byte[] bytes = str2Bcd(key);
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(bytes);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        // 真正开始解密操作
        byte[] result = cipher.doFinal(Base64.decodeBase64(data));
        return new String(result, "utf-8").trim();
    }

    public static byte[] hexStringToByteArray(String text) {
        if (text == null)
            return null;
        byte[] result = new byte[text.length() / 2];
        for (int i = 0; i < result.length; ++i) {
            int x = Integer.parseInt(text.substring(i * 2, i * 2 + 2), 16);
            result[i] = x <= 127 ? (byte) x : (byte) (x - 256);
        }
        return result;
    }

    public static String byteArrayToHexString(byte data[]) {
        String result = "";
        for (int i = 0; i < data.length; i++) {
            int v = data[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                result += "0";
            }
            result += hv;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
//        Map<String,Object> resultMap=new HashMap<>();
//      String companyAuthResult="{\"charge\":0,\"data\":{\"authCode\":\"00\",\"authMsg\":\"一致\",\"status\":\"存续\"},\"orderId\":\"WS2020072811152005601562\",\"resultCode\":\"200\",\"shiyiOrderId\":\"SY2020072811150646265909\",\"message\":\"请求成功\"}";
//
//
//        JSONObject auth4Object = JSONObject.parseObject(companyAuthResult);
//        if (auth4Object != null && "200".equals(auth4Object.getString("resultCode"))) {
//            JSONObject data = JSONObject.parseObject(auth4Object.getString("data"));
//            if (data != null) {
//                String auth_code = data.getString("authCode");
//                String authMsg = data.getString("authMsg");
//                if (!StringUtils.equals(auth_code, "00")) {
//                    resultMap.put("response_code", auth_code);
//                    resultMap.put("message", authMsg);
//                }
//            } else {
//                resultMap.put("response_code", "99");
//                resultMap.put("message", "认证失败，认证信息不符，请核对再试!");
//            }
//        }
//        System.out.println(resultMap.toString());
//        if(resultMap.isEmpty()){
//            System.out.println("==================");
//        }

        String s="70";
        BigDecimal bigDecimal=new BigDecimal(s);
        BigDecimal bigDecimal1=new BigDecimal("70");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.compareTo(bigDecimal1));
    }

}
