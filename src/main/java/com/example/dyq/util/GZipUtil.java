package com.example.dyq.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author: dongyuqiang
 * @date: 2020/8/20 18:03
 * @description:
 */
public class GZipUtil {

    public static void main(String[] args) {
        String s = "DB3244CB8DC76C7844C404C1D9B202D9E31F5FC0B8DDEC8E0685A4680FC95D0EA0BC87A1940A0FB8F2DD483CC9A426BACB49075D38E1D8B7BD1E396737481DE27311C66CB0D7EE9888C1E1773B74FA77EE31CB02923027671071BD5580C38DFFF08CBA3ADC93BA62B6A30FC3EB687D07EF8ADD93B391C3E4006D9C7CCBA96E2E1E99FF33EA7766A2967C84E35E51D1477680C108ED95FC48ABBE43555A890C72B3F0C1E364421F521368734741F3CA831DC5E43B7DD68C564CB43CE22CEEF606F75F7C97DE1C079A2738AB61386EE8BDB04257818464E7C5C1869F4761D485693346BE110898C4ABFA7C94E4B1945B5AA4E0A61C056FACE9A9C7D922D4B037E7F65E983767D5E0E5D1EA2575EAA8A2A365AA8C92ED7D4D97CA7304835DE9A32124EFC41D5F5DC32C78633501E73E0DA26CDD41F2477E6C1C9A54A22D1BFE280C411ECB1174F5DE2A9D1FD93DEF6CA7262DE3BCA496D0A0F8AD2C4D326804F426CB1B321FB2682DC8";
        System.out.println(s.length());
        String compress = compress(s);
        System.out.println(compress.length());

        byte[] bytes = String_BCD(s);
        System.out.println(new String(bytes).length());
    }

    /**
     * 使用gzip压缩字符串
     *
     * @param str 要压缩的字符串
     * @return
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * 使用gzip解压缩
     *
     * @param compressedStr 压缩字符串
     * @return
     */
    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }


    /**
     * 将十进制字符串转换为 BCD编码
     * @param str
     * @return
     */
    public static byte[] String_BCD(String str) {
        byte[] s = str.getBytes();
        byte[] b = new byte[s.length/2];
        for(int i=0;i<b.length;i++) {
            b[i] = (byte) (s[2*i] << 4 | (s[2*i+1] & 0xf));
        }
        return b;
    }
    /**
     * 将BCD编码的byte数组转换为String
     * @param bcd
     * @return
     */
    public static String BCD_String(byte[] bcd) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bcd.length;i++) {
            sb.append(bcd[i]>>4&0xf)
                    .append(bcd[i]&0xf);
        }
        return sb.toString();
    }


}
