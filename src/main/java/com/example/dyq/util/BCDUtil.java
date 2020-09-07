package com.example.dyq.util;

import java.io.ByteArrayOutputStream;

/**
 * @author: dongyuqiang
 * @date: 2020/9/7 11:21
 * @description:
 */
public class BCDUtil {

    public static void main(String[] args) {
        String data="018A391E9AAA96F6FE49912FEF408057EB7CBBA358C9FC0D41F4E6AAFF39462BD5CF26BA7130889787B7725DEEE6D3D5B6A3B47A4FF3416FAC95D1DBBCB36107345C526F4C4AC74679278D527E4C7BC668A268FA9FF5F90D6A75FF4D9534CA778C64A620D934338D95C7F75D2A3112DCE5814B4B23600CAB2704FF9B0CE818B386D90BDE7708FF7614102E85A19DF704069A3279228012E0B6D8278B742A168661B23014A29C8D8DE804C6C147EB122054C04D961AECE309EF3C755C5ABB5CBF61CF42118E74DC71412E59762CFAFC35B4C3F3611C51160AE1AB51E2A0AF54BA3C308B4D6D4BF83DFCCCB4313D9B8D78FDF0D186C6BB9EEC4B31C271436304E44295D56D47F445D35D25216441F28DE28F046744B7147B40FBCFA30411B0E9C4C065CB6C0B13D52A38C2E76E014B76ECDDC346E10231714CEFCDAC1D037CAA88DA46FDBD08B88A16992B7EA645102D9FEA49196C45C36826";
        byte[] bytes = StrToBCDBytes(data);

        String s = bcdToString(bytes);
        System.out.println(s);
    }



    /**
     * 将String转成BCD码
     *
     * @param s
     * @return
     */
    public static byte [] StrToBCDBytes(String s)
    {

        if(s.length () % 2 != 0)
        {
            s = "0" + s;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        char [] cs = s.toCharArray ();
        for (int i = 0;i < cs.length;i += 2)
        {
            int high = cs [i] - 48;
            int low = cs [i + 1] - 48;
            baos.write (high << 4 | low);
        }
        return baos.toByteArray ();
    }
    /**
     * 将BCD码转成String
     *
     * @param b
     * @return
     */
    public static String bcdToString(byte [] b){
        StringBuffer sb = new StringBuffer ();
        for (int i = 0;i < b.length;i++ )
        {
            int h = ((b [i] & 0xff) >> 4) + 48;
            sb.append ((char) h);
            int l = (b [i] & 0x0f) + 48;
            sb.append ((char) l);
        }
        return sb.toString () ;
    }

}
