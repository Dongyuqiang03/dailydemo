package com.example.dyq.util;

import java.util.Random;
import java.util.UUID;

/** 
 * 类说明:UUID生成
 */
public class UUIDUtil {
	
	private static String UUIDKEYS = "0123456789ABCDEF0123456789ABCDEF";

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 产生32个字节长度的随机字符串，由0-9，A-F组成，大写 
	 */
	public static String get32Key() {
		int len = UUIDKEYS.length();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < 32; i++) {
	       sb.append(UUIDKEYS.charAt((int) Math.round(Math.random() * (len - 1))));
	    }
	    return sb.toString();
	}

	/**
	 * 随机生成A-Z,a-z,0-9的num长的字符串
	 * @param num
	 * @return
	 */
	public static synchronized String getRandomString(int num){
		String range = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcedfghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < num; i++) {
			result.append( range.charAt( random.nextInt( range.length() ) ) );
		}
		return result.toString();
	}

	public static synchronized String getRandomIntString(int num){
		String range = "1234567890";
		Random random = new Random();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < num; i++) {
			result.append( range.charAt( random.nextInt( range.length() ) ) );
		}
		return result.toString();
	}

	public static void main(String[] a){
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());
		System.out.println(get32Key());

		System.out.println(getRandomString(16));
		System.out.println(getRandomIntString(8));
	}
}
