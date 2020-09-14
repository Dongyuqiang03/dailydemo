package com.example.dyq.util;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类说明:日期处理类
 */
public class DateUtil {
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_2 = "yyyyMMddHHmmss";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_FORMAT_3 = "yyyyMMdd";
	/**
	 * HHmmss
	 */
	public static final String DATE_FORMAT_4 = "HHmmss";
	/**
	 * HH
	 */
	public static final String DATE_FORMAT_5 = "HH";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_6 = "yyyy-MM-dd";
	/**
	 * yyyyMMddHHmm
	 */
	public static final String DATE_FORMAT_7 = "yyyyMMddHHmm";
	/**
	 * MMddHHmmss
	 */
	public static final String DATE_FORMAT_8 = "MMddHHmmss";
	/**
	 * yyyyMMdd HH:mm:ss
	 */
	public static final String DATE_FORMAT_9 = "yyyyMMdd HH:mm:ss";

	/**
	 * yyyy.MM.dd
	 */
	public static final String DATE_FORMAT_10 = "yyyy";
	
	private static Map<String, ThreadLocal<SimpleDateFormat>> mapThreadLocal = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
	private static final Object lockObj = new Object();
	private static DateUtil classInstance = new DateUtil();

	public static DateUtil getInstance() {
		return classInstance;
	}

	public static SimpleDateFormat getSimpleDateFormat(final String pattern) {
		ThreadLocal<SimpleDateFormat> df = mapThreadLocal.get(pattern);
		if (df == null) {
			synchronized (lockObj) {
				df = mapThreadLocal.get(pattern);
				if (df == null) {
					df = new ThreadLocal<SimpleDateFormat>() {
						@Override
						protected SimpleDateFormat initialValue() {
							return new SimpleDateFormat(pattern);
						}
					};
					mapThreadLocal.put(pattern, df);
				}
			}
		}
		return df.get();
	}
	
	/**
	 * 当前时间大于22点45分，返回true，否则返回false；
	 */
	public static boolean checkT0Time(){
		Date t = new Date();
		java.util.Calendar cal1 = java.util.Calendar.getInstance();   
	    cal1.setTime(t);   
	    cal1.set(java.util.Calendar.HOUR_OF_DAY, 22);
	    cal1.set(java.util.Calendar.MINUTE, 45);
	    cal1.set(java.util.Calendar.SECOND, 00);
	    
	    java.util.Calendar cal2 = java.util.Calendar.getInstance();   
	    cal2.setTime(t);
	    
	    if((cal2.getTimeInMillis() - cal1.getTimeInMillis()) > 0)
	    	return true;
	    
	    return false;
    }
	
	
	
	/**
	 * 日期转字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		String riqi = null;
		if(date != null && StringUtils.isNotBlank(pattern)){
			SimpleDateFormat dateFormat = getSimpleDateFormat(pattern);
			riqi =  dateFormat.format(date);
		}
		return riqi;
	}
	
	/**
	 * 字符串转日期
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDate(String date,String pattern) throws ParseException{
		Date rq = null;
		if(StringUtils.isNotBlank(date) && StringUtils.isNotBlank(pattern)){
			SimpleDateFormat dateFormat = getSimpleDateFormat(pattern);
			try {
				rq = dateFormat.parse(date);
			} catch (ParseException e) {
				throw e;
			}
		}
		return rq;
	}
	
	/**
	 * 获取当前时间
	 * @param pattern : 格式化
	 * @return
	 */
	public static String getTime(String pattern) {
		return getSimpleDateFormat(pattern).format(new Date());
	}
	
	/**
     * 判断时间是不是今天
     * @param date
     * @return
     */
    public static boolean isToday(Date date){
        Date now = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sim.format(now);
        String day = sim.format(date);
        return day.equals(nowDay);

    }
    
    /**
     * 判断时间是不是今天
     * @param date
     * @return
     * @throws ParseException 
     */
    public static boolean isToday(String sdate) throws ParseException{
        if(StringUtils.isEmpty(sdate)){
            return false;
        }
        Date now = new Date();
        Date date =formatDate(sdate,DATE_FORMAT_1);
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sim.format(now);
        String day = sim.format(date);
        return day.equals(nowDay);

    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
	
	 public static void main(String[] args) throws ParseException {

//		 System.out.println(DateUtil8.getNowDateWithFormat(DateUtil8.MMddHHmmss));
//		 System.out.println(DateUtil.getSimpleDateFormat(DateUtil.DATE_FORMAT_8).format(new Date()));
//		 Long time= new Date().getTime();
//		 Calendar calendar = Calendar.getInstance();
//		 calendar.setTimeInMillis(time);
//		 //输出年
//		 DateFormat df = new SimpleDateFormat("yyyy-MM");
//		 long thismonth =df.parse(df.format(calendar.getTime())).getTime();
//		 calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
//		 calendar.set(Calendar.DAY_OF_MONTH, 1);
//		 long nextmonth = df.parse(df.format(calendar.getTime())).getTime();
//
//		 System.out.println(thismonth);
//		 System.out.println(nextmonth);
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		 Date date_table = new Date();
//		 String table_time = sdf.format(date_table.getTime() - 1000 * 60);
//		 String trans_running_Start_Sub = table_time.substring(0, 7);
//		 System.out.println(trans_running_Start_Sub);
//		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		 Long time = new Date().getTime();
//		 Calendar calendar = Calendar.getInstance();
//		 calendar.setTimeInMillis(time);
//		 calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
//		 calendar.set(Calendar.DAY_OF_MONTH,1);
//		 long nextmonth  = df.parse(df.format(calendar.getTime())).getTime();
//		 System.out.println(nextmonth);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date_table = new Date();
		 String table_time = sdf.format(date_table.getTime() - 1000 * 60);
		 String trans_running_Start_Sub = table_time.substring(0, 7);
		 trans_running_Start_Sub = trans_running_Start_Sub.replace("-", "");
		 System.out.println(trans_running_Start_Sub);


	 }
}
