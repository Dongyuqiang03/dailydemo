package com.example.dyq.util;

import crypto.sm.SM4;
import crypto.sm.SMUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 加密工具类
 * @author Elson
 * 
 */
public class EncryptUtil {
	
	protected static Logger logger= LoggerFactory.getLogger(Httpclient.class);
	/**
	 * SHA256加密
	 * 
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String sha2Encrypt(String text) {
		MessageDigest sha2 = null;
		try {
			sha2 = MessageDigest.getInstance("SHA-256");
			return hex(sha2.digest(text.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public static String sha1Encrypt(String text) {
		MessageDigest sha2 = null;
		try {
			sha2 = MessageDigest.getInstance("SHA1");
			return hex(sha2.digest(text.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * MD5加密
	 * 
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5Encrypt(String text) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			return hex(md5.digest(text.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
//	public static String md5EncryptNew(String text) {
//		MessageDigest md5 = null;
//		try {
//			md5 = MessageDigest.getInstance("MD5");
//			return ISOMsgUtil.byte2hex(md5.digest(text.getBytes("utf-8")));
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//		return null;
//
//	}

	/**
	 * 返回16进制字符串
	 * 
	 * @param arr
	 * @return
	 */
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}
	
	
	public static final String KEY_MD5 = "MD5";
	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5   
	 * HmacSHA1   
	 * HmacSHA256   
	 * HmacSHA384   
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";



	
	/**
	 * MD5加密
	 * 
	 * @param data
	 *            = 需要加密的字符数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}


	private static String CHARSET = "UTF-8";
	private static SM4 sm4 = new SM4();
	private static String keyStr="NANNINGZULIN2021";
	/**
	 *
	 * SM4加密
	 * @param dataStr
	 * @return
	 */
	public static String SM4Encode(String dataStr) {
		try {
			return Base64.encodeBase64String(sm4.encryptEcb(SMUtil.PKCS7Padding(dataStr.getBytes(CHARSET),dataStr.getBytes(CHARSET).length), keyStr.getBytes(CHARSET)));

		} catch (Exception e) {
			e.printStackTrace();
			return dataStr;
		}
	}

	/**
	 * SM4解密
	 * @param dataStr
	 * @return
	 */
	public static String SM4Decode(String dataStr){
		try {
			byte[] plantext= Base64.decodeBase64(dataStr.getBytes(CHARSET));
			byte[] decodeData=sm4.decryptEcb(plantext, keyStr.getBytes(CHARSET));
			byte[] aa=SMUtil.PKCS7Cutting(decodeData,decodeData.length);
			return new String(aa,CHARSET);
		}catch(Exception e){
			e.printStackTrace();
			return dataStr;
		}
	}


	public static void main(String[] args) {
		String data="This is 一段明文内容！";
		System.out.println(SM4Encode(data));
		//yyMZpJOdy0qiI/sdJLoNfvFtiSy8tI5USUxjdfK5Bv8=
		//FRUXixwIjvJvDtb61aTwdR4pCTCaQLPRQ6ZPYH0eMUs=
	}
}
