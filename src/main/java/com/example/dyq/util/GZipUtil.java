package com.example.dyq.util;

import org.apache.commons.lang3.StringUtils;

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
        String data="018A391E9AAA96F6FE49912FEF408057EB7CBBA358C9FC0D41F4E6AAFF39462BD5CF26BA7130889787B7725DEEE6D3D5B6A3B47A4FF3416FAC95D1DBBCB36107345C526F4C4AC74679278D527E4C7BC668A268FA9FF5F90D6A75FF4D9534CA778C64A620D934338D95C7F75D2A3112DCE5814B4B23600CAB2704FF9B0CE818B386D90BDE7708FF7614102E85A19DF704069A3279228012E0B6D8278B742A168661B23014A29C8D8DE804C6C147EB122054C04D961AECE309EF3C755C5ABB5CBF61CF42118E74DC71412E59762CFAFC35B4C3F3611C51160AE1AB51E2A0AF54BA3C308B4D6D4BF83DFCCCB4313D9B8D78FDF0D186C6BB9EEC4B31C271436304E44295D56D47F445D35D25216441F28DE28F046744B7147B40FBCFA30411B0E9C4C065CB6C0B13D52A38C2E76E014B76ECDDC346E10231714CEFCDAC1D037CAA88DA46FDBD08B88A16992B7EA645102D9FEA49196C45C36826";
        String compress = compress(data);
        System.out.println(compress.length());

        String uncompress = uncompress(compress);
        System.out.println(uncompress);
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


    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]);
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     *  Ascii转换为字符串
     * @param value
     * @return
     */
    public static String asciiTransformString(String value){
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    /**
     * <编码>
     * <数字字符串编成BCD格式字节数组>
     * @param bcd 数字字符串
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static byte[] str2bcd(String bcd)
    {
        if (StringUtils.isEmpty(bcd))
        {
            return null;
        }
        else
        {
            // 获取字节数组长度
            int size = bcd.length() / 2;
            int remainder = bcd.length() % 2;

            // 存储BCD码字节
            byte[] bcdByte = new byte[size + remainder];

            // 转BCD码
            for (int i = 0; i < size; i++)
            {
                int low = Integer.parseInt(bcd.substring(2 * i, 2 * i + 1));
                int high = Integer.parseInt(bcd.substring(2 * i + 1, 2 * i + 2));
                bcdByte[i] = (byte)((high << 4) | low);
            }

            // 如果存在余数，需要填F
            if (remainder > 0)
            {
                int low = Integer.parseInt(bcd.substring(bcd.length() - 1));
                bcdByte[bcdByte.length - 1] = (byte)((0xf << 4) | low);
            }

            // 返回BCD码字节数组
            return bcdByte;
        }
    }

    /**
     * <解码>
     * <BCD格式的字节数组解成数字字符串>
     * @param bcd 字节数组
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String bcd2str(byte[] bcd)
    {
        if (null == bcd || bcd.length == 0)
        {
            return "";
        }
        else
        {
            // 存储转码后的字符串
            StringBuilder sb = new StringBuilder();

            // 循环数组解码
            for (int i = 0; i < bcd.length; i++)
            {
                // 转换低字节
                int low = (bcd[i] & 0x0f);
                sb.append(low);

                // 转换高字节
                int high = ((bcd[i] & 0xf0) >> 4);

                // 如果高字节等于0xf说明是补的字节，直接抛掉
                if (high != 0xf)
                {
                    sb.append(high);
                }
            }

            // 返回解码字符串
            return sb.toString();
        }
    }
}
