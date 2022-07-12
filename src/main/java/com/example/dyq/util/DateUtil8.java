package com.example.dyq.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.*;

/**
 * java1.8 的新特性，解决SimpleDateFormat的线程问题<br>
 * <li>Instant代替 Date，</li>
 * <li>LocalDateTime代替 Calendar，</li>
 * <li>DateTimeFormatter 代替 SimpleDateFormat.</li> 注意：如果是共享变量，则可能会出现线程问题。<br>
 *
 * @author zero 2019/03/30
 */
public class DateUtil8 {
    // 时间元素
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String WEEK = "week";
    private static final String DAY = "day";
    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";
    private static final String SECOND = "second";

    // 星期元素
    private static final String MONDAY = "MONDAY";// 星期一
    private static final String TUESDAY = "TUESDAY";// 星期二
    private static final String WEDNESDAY = "WEDNESDAY";// 星期三
    private static final String THURSDAY = "THURSDAY";// 星期四
    private static final String FRIDAY = "FRIDAY";// 星期五
    private static final String SATURDAY = "SATURDAY";// 星期六
    private static final String SUNDAY = "SUNDAY";// 星期日

    // 根据指定格式显示日期和时间
    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter yyyyMMdd_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * yyyy-MM-dd HH
     */
    private static final DateTimeFormatter yyyyMMddHH_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    /**
     * yyyy-MM-dd HH:mm
     */
    private static final DateTimeFormatter yyyyMMddHHmm_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter yyyyMMddHHmmss_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * HH:mm:ss
     */
    private static final DateTimeFormatter HHmmss_EN = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * yyyyMMdd
     */
    private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * yyyyMMddHH
     */
    private static final DateTimeFormatter yyyyMMddHH = DateTimeFormatter.ofPattern("yyyyMMddHH");
    /**
     * yyyyMMddHHmm
     */
    private static final DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    /**
     * yyyyMMddHHmmss
     */
    private static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    /**
     * HHmmss
     */
    private static final DateTimeFormatter HHmmss = DateTimeFormatter.ofPattern("HHmmss");

    /**
     * MMddHHmmss
     */
    public static final DateTimeFormatter MMddHHmmss = DateTimeFormatter.ofPattern("MMddHHmmss");


    /**
     * yyyy年MM月dd日
     */
    private static final DateTimeFormatter yyyyMMdd_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    /**
     * yyyy年MM月dd日HH时
     */
    private static final DateTimeFormatter yyyyMMddHH_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时");
    /**
     * yyyy年MM月dd日HH时mm分
     */
    private static final DateTimeFormatter yyyyMMddHHmm_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分");
    /**
     * yyyy年MM月dd日HH时mm分ss秒
     */
    private static final DateTimeFormatter yyyyMMddHHmmss_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
    /**
     * HH时mm分ss秒
     */
    private static final DateTimeFormatter HHmmss_CN = DateTimeFormatter.ofPattern("HH时mm分ss秒");



    // 本地时间显示格式：区分中文和外文显示
    private static final DateTimeFormatter shotDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    private static final DateTimeFormatter fullDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    private static final DateTimeFormatter longDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    private static final DateTimeFormatter mediumDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);


    /**
     * 根据format获取当前时间
     * @param dateTimeFormatter
     * @return
     */
    public static String getNowDateWithFormat(DateTimeFormatter dateTimeFormatter){
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    /**
     * 根据日期格式，获取当前时间
     *
     * @param formatStr 日期格式<br>
     *                  <li>yyyy</li>
     *                  <li>yyyy-MM-dd</li>
     *                  <li>yyyy-MM-dd HH:mm:ss</li>
     *                  <li>HH:mm:ss</li>
     * @return
     * @author zero 2019/03/30
     */
    public static String getTime(String formatStr) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatStr));
    }

    /**
     * 获取当前日期的节点时间（年，月，周，日，时，分，秒）
     *
     * @param node 日期中的节点元素（年，月，周，日，时，分，秒）
     * @return 节点数字，如创建此方法的时间：年 2019，月 3，日 30，周 6
     * @author zero 2019/03/30 星期六
     */
    public static Integer getNodeTime(String node) {
        LocalDateTime today = LocalDateTime.now();
        Integer resultNode = null;
        switch (node) {
            case YEAR:
                resultNode = today.getYear();
                break;
            case MONTH:
                resultNode = today.getMonthValue();
                break;
            case WEEK:
                resultNode = transformWeekEN2Num(String.valueOf(today.getDayOfWeek()));
                break;
            case DAY:
                resultNode = today.getDayOfMonth();
                break;
            case HOUR:
                resultNode = today.getHour();
                break;
            case MINUTE:
                resultNode = today.getMinute();
                break;
            case SECOND:
                resultNode = today.getSecond();
                break;
            default:
                // 当前日期是当前年的第几天。例如：2019/1/3是2019年的第三天
                resultNode = today.getDayOfYear();
                break;
        }
        return resultNode;
    }

    /**
     * 将英文星期转换成数字
     *
     * @param enWeek 英文星期
     * @return int，如果数字小于0，则检查，看是否输入错误 or 入参为null
     * @author zero 2019/03/30
     */
    public static int transformWeekEN2Num(String enWeek) {
        if (MONDAY.equals(enWeek)) {
            return 1;
        } else if (TUESDAY.equals(enWeek)) {
            return 2;
        } else if (WEDNESDAY.equals(enWeek)) {
            return 3;
        } else if (THURSDAY.equals(enWeek)) {
            return 4;
        } else if (FRIDAY.equals(enWeek)) {
            return 5;
        } else if (SATURDAY.equals(enWeek)) {
            return 6;
        } else if (SUNDAY.equals(enWeek)) {
            return 7;
        } else {
            return -1;
        }
    }

    /**
     * 获取当前日期之后（之后）的节点事件<br>
     * <ul>
     * 比如当前时间为：2019-03-30 10:20:30
     * </ul>
     * <li>node="hour",num=5L:2019-03-30 15:20:30</li>
     * <li>node="day",num=1L:2019-03-31 10:20:30</li>
     * <li>node="year",num=1L:2020-03-30 10:20:30</li>
     *
     * @param node 节点元素（“year”,"month","week","day","huor","minute","second"）
     * @param num  第几天（+：之后，-：之前）
     * @return 之后或之后的日期
     * @author zero 2019/03/30
     */
    public static String getAfterOrPreNowTime(String node, Long num) {
        LocalDateTime now = LocalDateTime.now();
        if (HOUR.equals(node)) {
            return now.plusHours(num).format(yyyyMMddHHmmss_EN);
        } else if (DAY.equals(node)) {
            return now.plusDays(num).format(yyyyMMddHHmmss_EN);
        } else if (WEEK.equals(node)) {
            return now.plusWeeks(num).format(yyyyMMddHHmmss_EN);
        } else if (MONTH.equals(node)) {
            return now.plusMonths(num).format(yyyyMMddHHmmss_EN);
        } else if (YEAR.equals(node)) {
            return now.plusYears(num).format(yyyyMMddHHmmss_EN);
        } else if (MINUTE.equals(node)) {
            return now.plusMinutes(num).format(yyyyMMddHHmmss_EN);
        } else if (SECOND.equals(node)) {
            return now.plusSeconds(num).format(yyyyMMddHHmmss_EN);
        } else {
            return "Node is Error!";
        }
    }

    /**
     * 获取与当前日期相距num个之后（之前）的日期<br>
     * <ul>
     * 比如当前时间为：2019-03-30 10:20:30的格式日期
     * <li>node="hour",num=5L:2019-03-30 15:20:30</li>
     * <li>node="day",num=1L:2019-03-31 10:20:30</li>
     * <li>node="year",num=1L:2020-03-30 10:20:30</li>
     * </ul>
     *
     * @param dtf  格式化当前时间格式（dtf = yyyyMMddHHmmss_EN）
     * @param node 节点元素（“year”,"month","week","day","huor","minute","second"）
     * @param num  （+：之后，-：之前）
     * @return 之后之前的日期
     * @author zero 2019/03/30
     */
    public static String getAfterOrPreNowTimePlus(DateTimeFormatter dtf, String node, Long num) {
        LocalDateTime now = LocalDateTime.now();
        if (HOUR.equals(node)) {
            return now.plusHours(num).format(dtf);
        } else if (DAY.equals(node)) {
            return now.plusDays(num).format(dtf);
        } else if (WEEK.equals(node)) {
            return now.plusWeeks(num).format(dtf);
        } else if (MONTH.equals(node)) {
            return now.plusMonths(num).format(dtf);
        } else if (YEAR.equals(node)) {
            return now.plusYears(num).format(dtf);
        } else if (MINUTE.equals(node)) {
            return now.plusMinutes(num).format(dtf);
        } else if (SECOND.equals(node)) {
            return now.plusSeconds(num).format(dtf);
        } else {
            return "Node is Error!";
        }
    }

    /**
     * 当前时间的hour，minute，second之后（之前）的时刻
     *
     * @param node 时间节点元素（hour，minute，second）
     * @param num  之后（之后）多久时，分，秒（+：之后，-：之前）
     * @return HH:mm:ss 字符串
     * @author zero 2019/03/30
     */
    public static String getAfterOrPreNowTimeSimp(String node, Long num) {
        LocalTime now = LocalTime.now();
        if (HOUR.equals(node)) {
            return now.plusHours(num).format(HHmmss_EN);
        } else if (MINUTE.equals(node)) {
            return now.plusMinutes(num).format(HHmmss_EN);
        } else if (SECOND.equals(node)) {
            return now.plusSeconds(num).format(HHmmss_EN);
        } else {
            return "Node is Error!";
        }
    }

    /**
     * 检查重复事件，比如生日。TODO This is a example.
     *
     * @param
     * @return
     * @author zero 2019/03/31
     */
    public static boolean isBirthday(int month, int dayOfMonth) {
        MonthDay birthDay = MonthDay.of(month, dayOfMonth);
        MonthDay curMonthDay = MonthDay.from(LocalDate.now());// MonthDay只存储了月、日。
        if (birthDay.equals(curMonthDay)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前日期第index日之后(之前)的日期（yyyy-MM-dd）
     *
     * @param index 第index天
     * @return 日期字符串：yyyy-MM-dd
     * @author zero 2019/03/31
     */
    public static String getAfterOrPreDayDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.DAYS).format(yyyyMMdd_EN);
    }

    /**
     * 获取当前日期第index周之前（之后）的日期（yyyy-MM-dd）
     *
     * @param index 第index周（+：之后，-：之前）
     * @return 日期字符串：yyyy-MM-dd
     * @author zero 2019/03/31
     */
    public static String getAfterOrPreWeekDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.WEEKS).format(yyyyMMdd_EN);
    }

    /**
     * 获取当前日期第index月之前（之后）的日期（yyyy-MM-dd）
     *
     * @param index 第index月（+：之后，-：之前）
     * @return 日期字符串：yyyy-MM-dd
     * @author zero 2019/03/31
     */
    public static String getAfterOrPreMonthDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.MONTHS).format(yyyyMMdd_EN);
    }

    /**
     * 获取当前日期第index年之前（之后）的日期（yyyy-MM-dd）
     *
     * @param index 第index年（+：之后，-：之前）
     * @return 日期字符串：yyyy-MM-dd
     * @author zero 2019/03/31
     */
    public static String getAfterOrPreYearDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.YEARS).format(yyyyMMdd_EN);
    }

    /**
     * 获取指定日期之前之后的第index的日，周，月，年的日期
     *
     * @param date  指定日期格式：yyyy-MM-dd
     * @param node  时间节点元素（日周月年）
     * @param index 之前之后第index个日期
     * @return yyyy-MM-dd 日期字符串
     * @author zero 2019/03/31
     */
    public static String getAfterOrPreDate(String date, String node, int index) {
        date = date.trim();
        if (DAY.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.DAYS).format(yyyyMMdd_EN);
        } else if (WEEK.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.WEEKS).format(yyyyMMdd_EN);
        } else if (MONTH.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.MONTHS).format(yyyyMMdd_EN);
        } else if (YEAR.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.YEARS).format(yyyyMMdd_EN);
        } else {
            return "Wrong date format!";
        }
    }

    /**
     * 检测：输入年份是否是闰年？
     *
     * @param date 日期格式：yyyy-MM-dd
     * @return true：闰年，false：平年
     * @author zero 2019/03/31
     */
    public static boolean isLeapYear(String date) {
        return LocalDate.parse(date.trim()).isLeapYear();
    }

    /**
     * 计算两个日期字符串之间相差多少个周期（天，月，年）
     *
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @param node  三者之一:(day，month,year)
     * @return 相差多少周期
     * @author zero 2019/03/31
     */
    public static int peridCount(String date1, String date2, String node) {
        date1 = date1.trim();
        date2 = date2.trim();
        if (DAY.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getDays();
        } else if (MONTH.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getMonths();
        } else if (YEAR.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getYears();
        } else {
            return 0;
        }
    }

    /**
     * 计算两个日期字符串之间相差多少个周期（天，月，年）
     *
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @param node  三者之一:(day，month,year)
     * @return 相差多少周期
     * @author zero 2019/03/31
     */
    public static int peridDateCount(Date date1, Date date2, String node) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (DAY.equals(node)) {
            return Period.between(localDate1,localDate2).getDays();
        } else if (MONTH.equals(node)) {
            return Period.between(localDate1,localDate2).getMonths();
        } else if (YEAR.equals(node)) {
            return Period.between(localDate1,localDate2).getYears();
        } else {
            return 0;
        }
    }

    /**
     * 切割日期。按照周期切割成小段日期段。例如： <br>
     *
     * @param startDate 开始日期（yyyy-MM-dd）
     * @param endDate   结束日期（yyyy-MM-dd）
     * @param period    周期（天，周，月，年）
     * @return 切割之后的日期集合
     * @author zero 2019/04/02
     * @example <li>startDate="2019-02-28",endDate="2019-03-05",period="day"</li>
     * <li>结果为：[2019-02-28, 2019-03-01, 2019-03-02, 2019-03-03, 2019-03-04, 2019-03-05]</li><br>
     * <li>startDate="2019-02-28",endDate="2019-03-25",period="week"</li>
     * <li>结果为：[2019-02-28,2019-03-06, 2019-03-07,2019-03-13, 2019-03-14,2019-03-20,
     * 2019-03-21,2019-03-25]</li><br>
     * <li>startDate="2019-02-28",endDate="2019-05-25",period="month"</li>
     * <li>结果为：[2019-02-28,2019-02-28, 2019-03-01,2019-03-31, 2019-04-01,2019-04-30,
     * 2019-05-01,2019-05-25]</li><br>
     * <li>startDate="2019-02-28",endDate="2020-05-25",period="year"</li>
     * <li>结果为：[2019-02-28,2019-12-31, 2020-01-01,2020-05-25]</li><br>
     */
    public static List<String> getPieDateRange(String startDate, String endDate, String period) {
        List<String> result = Lists.newArrayList();
        LocalDate end = LocalDate.parse(endDate, yyyyMMdd_EN);
        LocalDate start = LocalDate.parse(startDate, yyyyMMdd_EN);
        LocalDate tmp = start;
        switch (period) {
            case DAY:
                while (start.isBefore(end) || start.isEqual(end)) {
                    result.add(start.toString());
                    start = start.plusDays(1);
                }
                break;
            case WEEK:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    if (tmp.plusDays(6).isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + tmp.plusDays(6));
                    }
                    tmp = tmp.plusDays(7);
                }
                break;
            case MONTH:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfMonth = tmp.with(TemporalAdjusters.lastDayOfMonth());
                    if (lastDayOfMonth.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfMonth);
                    }
                    tmp = lastDayOfMonth.plusDays(1);
                }
                break;
            case YEAR:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfYear = tmp.with(TemporalAdjusters.lastDayOfYear());
                    if (lastDayOfYear.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfYear);
                    }
                    tmp = lastDayOfYear.plusDays(1);
                }
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 指定日期月的最后一天（yyyy-MM-dd）
     *
     * @param curDate     日期格式（yyyy-MM-dd）
     * @param firstOrLast true：第一天，false：最后一天
     * @return
     * @author zero 2019/04/13
     */
    public static String getLastDayOfMonth(String curDate, boolean firstOrLast) {
        if (firstOrLast) {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.firstDayOfMonth()).toString();
        } else {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.lastDayOfMonth()).toString();
        }
    }

    /**
     * 指定日期年的最后一天（yyyy-MM-dd）
     *
     * @param curDate     指定日期（格式：yyyy-MM-dd）
     * @param firstOrLast true:第一天，false:最后一天
     * @return
     * @author zero 2019/04/13
     */
    public static String getLastDayOfYear(String curDate, boolean firstOrLast) {
        if (firstOrLast) {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.firstDayOfYear()).toString();
        } else {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.lastDayOfYear()).toString();
        }
    }

    /**
     * 获取下一个星期的日期
     *
     * @param curDay          yyyy-MM-dd
     * @param dayOfWeek       monday:1~sunday:7
     * @param isContainCurDay 是否包含当天，true：是，false：不包含
     * @return 日期（yyyy-MM-dd）
     * @author zero 2019/04/02
     */
    public static String getNextWeekDate(String curDay, int dayOfWeek, boolean isContainCurDay) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (isContainCurDay) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.next(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * 获取上一个星期的日期
     *
     * @param curDay    指定日期（yyyy-MM-dd）
     * @param dayOfWeek 数字范围（monday:1~sunday:7）
     * @param isCurDay  是否包含当天，true：是，false：不包含
     * @return 日期（yyyy-MM-dd）
     * @author zero 2019/04/02
     */
    public static String getPreWeekDate(String curDay, int dayOfWeek, boolean isCurDay) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (isCurDay) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.previousOrSame(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.previous(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * 获取指定日期当月的最后或第一个星期日期
     *
     * @param curDay      指定日期（yyyy-MM-dd）
     * @param dayOfWeek   周几（1~7）
     * @param lastOrFirst true：最后一个，false本月第一个
     * @return 日期（yyyy-MM-dd）
     * @author zero 2019/04/02
     */
    public static String getFirstOrLastWeekDate(String curDay, int dayOfWeek, boolean lastOrFirst) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (lastOrFirst) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.lastInMonth(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.firstInMonth(DayOfWeek.of(dayOfWeek))).toString();
        }
    }


    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param time String类型日期
     * @return 时间戳
     */
    public static Long convertTimeToLong(String time, DateTimeFormatter dateTimeFormatter) {
        if (null == dateTimeFormatter || "".equals(dateTimeFormatter)) {
            dateTimeFormatter = yyyyMMddHHmmss_EN;
        }
        Assert.notNull(time, "time is null");
        LocalDateTime parse = LocalDateTime.parse(time, dateTimeFormatter);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取当前时间戳，精确到毫秒
     *
     * @return
     */
    public static Long getCurrentTimeStampEpochMilli() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取当前时间戳，精确到秒
     *
     * @return
     */
    public static Long getCurrentTimeStampEpochSecond() {
        return Instant.now().getEpochSecond();
    }


    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestamToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转时间戳 精确到毫秒
     *
     * @param localDateTime
     * @return
     */
    public static long  localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * LocalDate转时间戳 精确到毫秒
     *
     * @param localDate
     * @return
     */
    public static long  localDateToTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * localDateTime转String
     * @param localDateTime
     * @param dateTimeFormatter
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime,DateTimeFormatter dateTimeFormatter){
        return localDateTime.format(dateTimeFormatter);
    }
    /**
     * String类型时间戳转String类型日期
     * @param timeStamp
     * @return
     */
    public static String  stampToDate8(String timeStamp){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(timeStamp)), ZoneId.systemDefault());
        return localDateTimeToString(localDateTime,yyyyMMddHHmmss_EN);
    }

    /**
     * 取范围日期的随机日期时间,不含边界
     * @param startDay
     * @param endDay
     * @return
     */
    public static LocalDateTime randomLocalDateTime(int startDay,int endDay){

        int plusMinus = 1;
        if(startDay < 0 && endDay > 0){
            plusMinus = Math.random()>0.5?1:-1;
            if(plusMinus>0){
                startDay = 0;
            }else{
                endDay = Math.abs(startDay);
                startDay = 0;
            }
        }else if(startDay < 0 && endDay < 0){
            plusMinus = -1;

            //两个数交换
            startDay = startDay + endDay;
            endDay  = startDay - endDay;
            startDay = startDay -endDay;

            //取绝对值
            startDay = Math.abs(startDay);
            endDay = Math.abs(endDay);

        }

        LocalDate day = LocalDate.now().plusDays(plusMinus * RandomUtils.nextInt(startDay,endDay));
        int hour = RandomUtils.nextInt(1,24);
        int minute = RandomUtils.nextInt(0,60);
        int second = RandomUtils.nextInt(0,60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }

    /**
     * 取范围日期的随机日期时间,不含边界
     * @param startDay
     * @param endDay
     * @return
     */
    public static Date randomDateTime(int startDay,int endDay){
        LocalDateTime ldt = randomLocalDateTime(startDay,endDay);
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }





    public static void main(String[] args) throws Exception {
//        System.out.println("===================");
//        //获取距离当前日期90天前的日期的时间戳
//        String afterOrPreNowTimePlus = getAfterOrPreNowTimePlus(yyyyMMddHHmmss_EN, DAY, -90L);
//        System.out.println("当前日期：" + getNowDateWithFormat(yyyyMMddHHmmss_EN));
//        System.out.println("距离当前日期90日前的日期：" + afterOrPreNowTimePlus);
//        System.out.println("90日之前时间戳" + convertTimeToLong(afterOrPreNowTimePlus, null));
//        System.out.println("===================");
//
//        int i = Integer.valueOf("90");
//        Long time = System.currentTimeMillis();
//        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
//        time = dft.parse(dft.format(calendar.getTime())).getTime();
//        System.out.println("90日之前时间戳：" + time);
//
//        System.out.println("========================");
//        //获取当前时间点
//        System.out.println("当前时间" + getTime("HHmm"));
//        System.out.println("==================");
//        //获取当前日期的时间戳
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date_table = new Date();
//        String table_time = sdf.format(date_table.getTime());
//        Long dateStart = sdf.parse(table_time).getTime();
//        System.out.println("老版本当前日期时间戳：" + dateStart);
//        System.out.println("新版本时间戳1："+ localDateToTimestamp(LocalDate.now()));
//        System.out.println("==================");
//        //当天0点的时间戳
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        long startTime=cal.getTimeInMillis();// 当天0时
//        System.out.println("老版0时时间戳："+startTime);
//        LocalDateTime min = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
//        System.out.println("新版0时时间戳："+localDateTimeToTimestamp(min));
//        //当天24点的时间戳
//        cal.set(Calendar.HOUR_OF_DAY, 24);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        long endTime=cal.getTimeInMillis();// 当天24时
//        System.out.println("老版24时时间戳："+endTime);
//        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
//        System.out.println("新版24时时间戳："+localDateTimeToTimestamp(of));
//        System.out.println("==========");
//        DateTime end_date = DateTime.parse("2018-10-01", DateTimeFormat.forPattern("yyyy-MM-dd"));
//        DateTime noe = new DateTime(DateTime.now().toLocalDate().toString());
//        org.joda.time.Period p2 = new org.joda.time.Period(noe, end_date, PeriodType.days());
//        System.out.println("时间差："+(p2.getDays()>10));
//        System.out.println("==================");
//        System.out.println("当前日期：="+getNowDateWithFormat(yyyyMMddHHmmss_EN));
//
//        System.out.println("===============================");
//        String s = localDateTimeToString(LocalDateTime.now(), yyyyMMddHHmm_EN);
//        System.out.println("java8之后："+s);
        //1598889600000
//        String afterOrPreNowTimePlus = getAfterOrPreNowTimePlus(yyyyMMddHHmmss_EN, DAY, -7L);
//        System.out.println(afterOrPreNowTimePlus);
//        LocalDate localDate = LocalDate.now();
//        String currentTableSuffix = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        System.out.println(currentTableSuffix);
//        for(int i=1;i<=15;i++) {
//            LocalDate beforeLocalDate = localDate.minusDays(i);
//            String tableSuffix = beforeLocalDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//            System.out.println(tableSuffix);
//        }
//        Date date = randomDate("2021-09-18", "2021-10-18");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(simpleDateFormat.format(date));
//        Date date = randomDateTime(30, 60);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(simpleDateFormat.format(date));
//        System.out.println(peridDateCount(new Date(),date,DAY));

        System.out.println(LocalDateTime.now().plusDays(7));;
    }

}