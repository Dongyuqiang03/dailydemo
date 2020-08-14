package com.example.dyq.util;

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

	
//	public static void main(String[] args) {
//		//System.out.println(EncryptUtil.sha2Encrypt("w123123"));
//		//System.out.println(EncryptUtil.md5Encrypt("w123123"));  //-->9a51d81c744ca5c8b5632a63fbd0ede7
//		System.out.println(EncryptUtil.sha1Encrypt("123456"));
//	}
}
