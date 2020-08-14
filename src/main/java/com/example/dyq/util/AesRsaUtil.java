package com.example.dyq.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;


/**
 * AES及RSA加解密 作者：王政 创建时间：2017年1月19日 下午3:57:11
 */
public class AesRsaUtil {

    private static final Logger logger = LoggerFactory.getLogger(AesRsaUtil.class);

    public static final String SIGN_ALGORITHMS = "MD5withRSA";

    /**
     * 通过AES秘钥加密数据
     *
     * @param data    数据
     * @param aesKey  AES秘钥
     * @param charset 编码
     * @return AES秘钥加密数据
     * @throws UnsupportedEncodingException
     */
    public static String encryptData(String data, String aesKey, String charset) throws UnsupportedEncodingException {
        byte[] plainBytes = data.getBytes(charset);
        byte[] keyBytes = aesKey.getBytes(charset);
        return new String(Base64.encodeBase64((AESEncrypt(plainBytes, keyBytes, "AES", "AES/ECB/PKCS5Padding", null))), charset);
    }

    /**
     * 通过RSA公钥加密AES秘钥
     *
     * @param publicRSAKey RSA公钥
     * @param aesKey       AES秘钥
     * @param charset      编码
     * @return 加密后AES秘钥
     * @throws UnsupportedEncodingException
     */
    public static String encrtptKey(String publicRSAKey, String aesKey, String charset) throws UnsupportedEncodingException {
        byte[] keyBytes = aesKey.getBytes(charset);
        PublicKey yhPubKey = buildRSAPublicKeyByStr(publicRSAKey);
        return new String(Base64.encodeBase64(RSAEncrypt(keyBytes, yhPubKey, 2048, 11, "RSA/ECB/PKCS1Padding")), charset);
    }

    /**
     * 通过RSA私钥解密AES秘钥
     *
     * @param privateRSAKey RSA私钥
     * @param aesEncryptKey 经过RSA加密的AES秘钥
     * @param charset       编码
     * @return AES秘钥字节
     * @throws UnsupportedEncodingException
     */
    public static byte[] decryptKey(String privateRSAKey, String aesEncryptKey, String charset) throws UnsupportedEncodingException {
        byte[] decodeBase64KeyBytes = Base64.decodeBase64(aesEncryptKey.getBytes(charset));
        PrivateKey hzfPriKey = buildRSAPrivateKeyByStr(privateRSAKey);
        return RSADecrypt(decodeBase64KeyBytes, hzfPriKey, 2048, 11, "RSA/ECB/PKCS1Padding");
    }

    /**
     * 通过AES秘钥解密数据
     *
     * @param aesEncryptData 待解密AES数据
     * @param aesKeyBytes    AES秘钥字节
     * @param charset        编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decryptData(String aesEncryptData, byte[] aesKeyBytes, String charset) throws UnsupportedEncodingException {
        byte[] decodeBase64DataBytes = Base64.decodeBase64(aesEncryptData.getBytes(charset));
        byte[] aesDecryptData = AESDecrypt(decodeBase64DataBytes, aesKeyBytes, "AES", "AES/ECB/PKCS5Padding", null);
        return new String(aesDecryptData, charset);
    }

//    public static String decryptAesHex(String aesEncryptData, String key) throws Exception {
//        byte[] aesDecryptData = AESDecrypt(HexUtils.hex2byte(aesEncryptData), key.getBytes("UTF-8"), "AES", "AES/ECB/PKCS5Padding", null);
//        return new String(aesDecryptData, "UTF-8");
//    }


    public static byte[] AESEncrypt(byte[] plainBytes, byte[] keyBytes, String keyAlgorithm, String cipherAlgorithm, String IV) {
        try {
            if (keyBytes.length % 8 != 0 || keyBytes.length < 16 || keyBytes.length > 32) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
            if (trimToNull(IV) != null) {
                IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }

            byte[] encryptedBytes = cipher.doFinal(plainBytes);

            return encryptedBytes;
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return null;
    }

    public static byte[] RSAEncrypt(byte[] plainBytes, PublicKey publicKey, int keyLength, int reserveSize, String cipherAlgorithm) {
        int keyByteSize = keyLength / 8;
        int encryptBlockSize = keyByteSize - reserveSize;
        int nBlock = plainBytes.length / encryptBlockSize;
        if ((plainBytes.length % encryptBlockSize) != 0) {
            nBlock += 1;
        }

        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * keyByteSize);
            for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
                int inputLen = plainBytes.length - offset;
                if (inputLen > encryptBlockSize) {
                    inputLen = encryptBlockSize;
                }

                byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
                outbuf.write(encryptedBlock);
            }

            outbuf.flush();
            outbuf.close();
            return outbuf.toByteArray();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return null;
    }

    public static byte[] RSADecrypt(byte[] encryptedBytes, PrivateKey privateKey, int keyLength, int reserveSize, String cipherAlgorithm) {
        int keyByteSize = keyLength / 8;
        int decryptBlockSize = keyByteSize - reserveSize;
        int nBlock = encryptedBytes.length / keyByteSize;

        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * decryptBlockSize);
            for (int offset = 0; offset < encryptedBytes.length; offset += keyByteSize) {
                int inputLen = encryptedBytes.length - offset;
                if (inputLen > keyByteSize) {
                    inputLen = keyByteSize;
                }

                byte[] decryptedBlock = cipher.doFinal(encryptedBytes, offset, inputLen);
                outbuf.write(decryptedBlock);
            }

            outbuf.flush();
            outbuf.close();
            return outbuf.toByteArray();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return null;
    }

    public static byte[] AESDecrypt(byte[] encryptedBytes, byte[] keyBytes, String keyAlgorithm, String cipherAlgorithm, String IV) {
        try {
            if (keyBytes.length % 8 != 0 || keyBytes.length < 16 || keyBytes.length > 32) {
                return null;
            }

            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
            if (IV != null && trimToNull(IV) != null) {
                IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            }

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return decryptedBytes;
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return null;
    }

    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = {0, 0, 0}; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                cResult[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                cResult[i] = (char) ('a' + Math.random() * 26);
            else
                cResult[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }

    public static PublicKey buildRSAPublicKeyByStr(String key) {
        try {
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(pubX509);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return null;
    }

    public static PrivateKey buildRSAPrivateKeyByStr(String key) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(priPKCS8);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return null;
    }

    /**
     * 读取密钥文件内容
     *
     * @param
     * @return
     * @throws IllegalAccessException
     */
    public static String readKeyFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
            br.readLine();// 先读取第一行
            StringBuffer keyContent = new StringBuffer();
            String s = br.readLine();
            while (s.charAt(0) != '-') {
                keyContent.append(s);
                s = br.readLine();
            }
            br.close();
            return keyContent.toString();
        } catch (Exception e) {
            logger.error("密钥读取失败.\n" + e.getMessage(), e);
            return "";
        }
    }

    /**
     * 读取密钥文件内容(重载)
     *
     * @param inputStream
     * @return
     */
    public static String readKeyFile(InputStream inputStream) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            br.readLine();// 先读取第一行
            StringBuffer keyContent = new StringBuffer();
            String s = br.readLine();
            while (s.charAt(0) != '-') {
                keyContent.append(s);
                s = br.readLine();
            }
            br.close();
            return keyContent.toString();
        } catch (Exception e) {
            logger.error("密钥读取失败.\n" + e.getMessage(), e);
            return "";
        }
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }


    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }


    public static void main(String[] args) {
//		try {
//			System.out.println(decryptAesHex("0C7703785392783BA5CF59CF923D1321560C1F8265F4055C1E0A53F2FDFFFFBC", "1qaz2WSX3edc"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        try {
            String filePath = "D:\\companyProject\\posx-mrs2\\src\\main\\resources\\key\\wpPri.txt";
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStream = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStream);
            StringBuffer keyContent = new StringBuffer();
            String s = br.readLine();
            int size = s.length() / 64;
            if (s.length() % 64 != 0) {
                size += 1;
            }
            List<String> list = new ArrayList<String>();
            for (int index = 0; index < size; index++) {
                String childStr = substring(s, index * 64,
                        (index + 1) * 64);
                list.add(childStr);
            }
            br.close();
            for (String s1 : list) {
                System.out.println(s1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

}